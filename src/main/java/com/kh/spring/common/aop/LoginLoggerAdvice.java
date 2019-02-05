package com.kh.spring.common.aop;

import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
public class LoginLoggerAdvice {
	
	Logger logger = Logger.getLogger(getClass());
	
	@AfterReturning(pointcut="execution(* com.kh.spring.member..*Controller.*Login(..))", returning="returnObj")
	public void advice(JoinPoint joinPoint, Object returnObj) {
		ModelAndView mav = (ModelAndView)returnObj;
		Map<String, Object> map = mav.getModel();
		if(map.containsKey("memberLoggedIn")) {
			Member m = (Member)map.get("memberLoggedIn");
			logger.info("["+m.getMemberId()+"]이 로그인함.");
		}
	}
}
