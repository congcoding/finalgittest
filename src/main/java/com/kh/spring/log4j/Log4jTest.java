package com.kh.spring.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
	* %c : 카테고리명(logger이름)을 표시(full class명)
		* 카테고리명이 a.b.c일때, %c{2}는 b.c를 출력
	* %C : 클래스명을 표시함.	
		* 풀 클래스 명이 com.kh.logger일때, %C{2}는 kh.logger를 출력
	* %d : 로그 시간을 출력한다. java.text.SimpleDateFormat에서 적절한 출력 포맷을 지정할 수 있다. 
		* %d{HH:mm:ss, SSS}
		* %d{yyyy MMM dd HH:mm:ss, SSS}
		* %d{ABSOLUTE} 
		* %d{DATE} 
		* %d{ISO8601}
	* %F : 파일명을 출력. 로그시 수행한 메소드, 라인번호가 함께 출력된다.
	* %l : 로깅이 발생한 caller의 위치정보. 자바파일명:라인번호(링크제공) 
	* %L : 라인 번호만 출력한다(링크없음)
	* %m : 로그로 전달된 메시지를 출력한다.
	* %M : 로그를 수행한 메소드명을 출력한다. 
	* %n : 플랫폼 종속적인 개행문자가 출력. rn 또는 n
	* %p : 로그 이벤트명등의 priority 가 출력(debug, info, warn, error, fatal )
	* %r : 로그 처리시간 (milliseconds)
	* %t : 로그이벤트가 발생된 쓰레드의 이름을 출력
	* %% : % 표시를 출력. escaping
	* %r : 어플리케이션 시작 이후 부터 로깅이 발생한 시점의 시간(milliseconds)
	* %X : 로깅이 발생한 thread와 관련된 MDC(mapped diagnostic context)를 출력합니다. %X{key} 형태.

 * @author user2
 *
 */
public class Log4jTest {
	private Logger logger = Logger.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		new Log4jTest().test();
	}
	
	public void test() {
		logger.fatal("Fatal로그!");
		//아주 심각한 에러
		
		logger.error("Error로그!");
		//요청처리중 에러가 발생한 경우
		
		logger.warn("Warn로그!");
		//프로그램실행에는 당장 문제가 없지만, 문제소지 있음.
		
		logger.info("Info로그!");
		//상태변경과 같은 정보성 메세지
		
		logger.debug("Debug로그!");
		//개발모드 
		
		logger.trace("Trace로그!");
		//디버그레벨을 좀더 세분화하여 관리하기 위한 레벨
	}

}
