package com.nasoft.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasoft.log.bean.SystemLogInfo;
import com.nasoft.log.logger.SystemLogger;

@Aspect
@Component
public class SystemLogAspect {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	protected final Logger slogger = LoggerFactory.getLogger(this.getClass());
	private static final String SUCCESS = "00";
	private static final String FAILED = "99";
	
	@Autowired(required = false)
	private SystemLogger logger;
		
	@Pointcut("@annotation(com.nasoft.log.aspect.SystemLog)")
	public void aspectLog(){}
	
	@Around("aspectLog()")
	public Object afterInit(ProceedingJoinPoint pjp) throws Throwable {
		Object result = pjp.proceed();
		log(pjp, SUCCESS, null);
		return result;
	}
	
	@AfterThrowing(pointcut = "aspectLog()", throwing = "e")
	public void doAfterThrowing(JoinPoint jp, Throwable e) {
		log(jp, FAILED, e.getMessage());
	}
	
	
	public void log(JoinPoint jp, String status, String msg){
		if(null != logger){
			// 取得用户自定义信息
			SystemLogInfo logInfo = SystemLogInfo.getSystemLogInfo();
			String userId = null != logInfo ? logInfo.getUserId() : null;
			String ipaddress = null != logInfo ? logInfo.getIpaddress() : null;
			
			// 取得方法信息
			String targetName = jp.getTarget().getClass().getName();
			String methodName = jp.getSignature().getName();
			String type = null;
			String message = msg;
			Object[] arguments = jp.getArgs();
			String params;
			try {
				params = objectMapper.writeValueAsString(arguments);
			} catch (JsonProcessingException e) {
				params = null;
			}
			Class<?> targetClass = null;
			try {
				targetClass = Class.forName(targetName);
			} catch (ClassNotFoundException e) {
				slogger.error("日志记录发生错误", e);
				return;
			}
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class<?>[] clazzs = method.getParameterTypes();
					if (clazzs.length == arguments.length) {
						SystemLog anno = method.getAnnotation(SystemLog.class);
						type = !StringUtils.isBlank(anno.type()) ? anno.type() : null;
						message = StringUtils.isBlank(message) && !StringUtils.isBlank(anno.type()) ? anno.message() : message;
						break;
					}
				}
			}
			
			logger.log(userId, ipaddress, type, targetName, methodName, params, status, message, new Date());
		}
	}
}