package com.nasoft.log.aspect;

import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	/** 日志类型 **/
	public String type() default "";

	/** 自定义消息 **/
	public String message() default "";

}