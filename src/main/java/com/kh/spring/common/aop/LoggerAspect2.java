package com.kh.spring.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect2 {
	Logger logger = Logger.getLogger(getClass());
	
	@Pointcut("execution(* com.kh.spring.memo..*(..))")
	public void pointCutForAll() {
		
	}
	
	/**
	 * AroundAdvice
	 */
	@Around("pointCutForAll()")
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature signature = joinPoint.getSignature();
		logger.debug("signature="+signature);
		String type = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		logger.debug("type="+type);
		logger.debug("methodName="+methodName);
		
		String comp = "";
		if(type.indexOf("Controller") > -1) {
			comp="Controller \t: ";
		}
		else if(type.indexOf("Service") > -1) {
			comp="Service \t: ";
		}
		else if(type.indexOf("Dao") > -1) {
			comp="Dao \t: ";
		}
		
		logger.debug("[Before]"+comp+type+"."+methodName+"()");
		
		Object obj = joinPoint.proceed();
		
		logger.debug("[After]"+comp+type+"."+methodName+"()");
		
		return obj;
	}
}
