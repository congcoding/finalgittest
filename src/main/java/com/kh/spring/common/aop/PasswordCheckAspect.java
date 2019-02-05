package com.kh.spring.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.kh.spring.memo.model.exception.MemoException;

@Component
@Aspect
public class PasswordCheckAspect {
	Logger logger = Logger.getLogger(getClass());
	
	@Before("execution(* com.kh.spring.memo..*Controller.insert*(..)) or execution(* com.kh.spring.memo..*Controller.delete*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		//타겟메소드(joinPoint) 실행전 코드
		Object[] obj = joinPoint.getArgs();
		String password = String.valueOf(obj[1]);
		logger.debug("입력한 비밀번호 : ["+password+"]");
		if(password.trim().length() == 0) {
			throw new MemoException("패스워드가 유효하지 않습니다.");
		}
	}
}
