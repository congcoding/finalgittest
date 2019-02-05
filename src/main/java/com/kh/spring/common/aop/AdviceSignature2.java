package com.kh.spring.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @실습문제 : insertMemo메소드 실행시간 구하기
 */
//@Component
//@Aspect
public class AdviceSignature2 {
	private Logger logger = Logger.getLogger(AdviceSignature2.class);
		
//	@Pointcut("execution(* com.kh.spring.memo..*Controller.insert*(..))")
//	public void pointcut() {}
	
//	@Around("pointcut()")
	@Around("execution(* com.kh.spring.memo..*Controller.insert*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String methodName = joinPoint.getSignature().getName();
		
		//Target메소드 실행전 코드
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = joinPoint.proceed();
		
		//Target메소드 실행후 코드
		stopWatch.stop();
		logger.debug(methodName + "() 소요시간 : " + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return obj;
	}
}
