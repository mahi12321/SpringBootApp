package com.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {
	
	Logger log=LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.app.*.*.*(..))")
	public void myPointcut() {
		
	}
	
	@Around(value = "myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper=new ObjectMapper();
		String className=pjp.getTarget().getClass().toString();
		String methodName=pjp.getSignature().getName();
		Object[] args=pjp.getArgs();
		log.info(className+":"+methodName+"()"+mapper.writeValueAsString(args));
		Object object=pjp.proceed();
		log.info(className+":"+methodName+"()"+mapper.writeValueAsString(object));
		return object;
		
	}

}
