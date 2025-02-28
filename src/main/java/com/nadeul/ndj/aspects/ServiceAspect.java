package com.nadeul.ndj.aspects;

import java.util.Iterator;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
  * @FileName : ServiceAspect.java
  * @Project : ndj
  * @Date : 2023. 6. 7. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 공공 API 이용 시 서비스 키와 리턴 타입 파라미터 누락 시 aop 이용하여 값 할당
  */
@Aspect
@Component
public class ServiceAspect {
	
	@Value("${apiKey.tourApiKey}")
  private String tourApiKey;
	
		@SuppressWarnings("unchecked")
		@Before("execution(* com.nadeul.ndj.controller.*ServiceApiController.*(..))")
    public void mesajVerMetodundanOnce(JoinPoint joinPoint) {
			Map<String, Object> requestParam = null;
			for (Object arg : joinPoint.getArgs()) {
			    if (arg instanceof Map) {
			        requestParam = (Map<String, Object>) arg;
			        break;
			    }
			}
			
		if(requestParam != null) {
			Iterator<Map.Entry<String, Object>> iterator = requestParam.entrySet().iterator();
	            while (iterator.hasNext()) {
	                Map.Entry<String, Object> entry = iterator.next();
	                Object value = entry.getValue();
	                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
	                    iterator.remove();
	                }
	            }
	            
	        if (!requestParam.containsKey("_type") || (requestParam.get("_type") == null || requestParam.get("_type").toString().isEmpty())) {
	        	requestParam.put("_type", "json");
	        }

        	requestParam.put("serviceKey", tourApiKey);
        	requestParam.put("MobileApp", "NADRY");
        	requestParam.put("MobileOS", "ETC");
        	
        	
	    }
			
    }
   

}
