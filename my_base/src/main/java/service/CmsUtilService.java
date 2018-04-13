package service;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CmsUtilService extends BaseService{
	public enum ORDER{
		ASC("asc"),
		DESC("desc");
		
		private String order;
		private ORDER(String order){
			this.order = order;
		}
	
		public String orderString(){
			return this.order;
		}
		
	}
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;
	
	public void processOrder(Object example, Class<?> mapperedClass, String propName, ORDER order){
		Method orderMethod = getOrderMethod(example);
		
		if(null == orderMethod){
			throw new RuntimeException("object is not example class");
		}
		
		String fieldName = getFieldName(mapperedClass, propName);
		
		if(null == fieldName){
			throw new RuntimeException("maperedClass not has propname");
		}
		
		try {
			orderMethod.invoke(example, fieldName + " " + order.orderString());
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error("发生错误", e);
		}
	}
	
	private String getFieldName(Class<?> mapperedClass, String propName){
		Collection<ResultMap> rml = sqlSessionFactory.getConfiguration().getResultMaps();
		
		ResultMap rmx = null;
		for(Object rm : rml){
			if(ResultMap.class.isAssignableFrom(rm.getClass())){
				ResultMap rm1 = (ResultMap)rm;
				if(null != rm1 && null != rm1.getType() && mapperedClass.isAssignableFrom(rm1.getType())){
					rmx = (ResultMap)rm;
					break;
				}
			}
		}
		
		if(null != rmx){
			List<ResultMapping> mappings = rmx.getPropertyResultMappings();
			for(ResultMapping mapping : mappings){
				if(propName.equals(mapping.getProperty())){
					return mapping.getColumn();
				}
			}
		}
		
		return null;
	};
	
	private Method getOrderMethod(Object example){
		try {
			Method method = example.getClass().getMethod("setOrderByClause", String.class);
			
			if(null != method){
				return method;
			}
			
			return null;
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error("发生错误:", e);
			return null;
		}
	}
	
	
}
