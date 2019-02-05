package com.kh.spring.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class LoggerAspect {
	Logger logger = Logger.getLogger(getClass());
	
	/**
	 * AroundAdvice
	 */
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
