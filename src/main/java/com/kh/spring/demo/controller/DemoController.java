package com.kh.spring.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/**
 * @Controller 클래스 메소드가 가질 수 있는 파라미터
 * - HttpServletRequest
 * - HttpServletResponse
 * - HttpSession
 * 
 * - InputStream/Reader : 요청에 대한 입력
 * - OutputStream/Writer : 응답에 대한 출력
 * 
 * - @PathVariable
 * - @RequestParam
 * - @RequestHeader
 * - @CookieValue
 * - @RequestBody
 * 
 * - Map, Model, ModelMap : View에 전달한 모델데이터를 가진 객체
 * - Command객체 - vo객체
 * - Error, BindingResult : 유효성 검사 관련 객체
 * - SessionStatus : 세션관리객체
 * - java.util.Locale : 서버 지역 정보
 *
 */
@Controller
public class DemoController {

	//DI : 스프링은 빈을 관리시, 기본적으로 싱글턴을 처리함
	@Autowired
	DemoService demoService;
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo"; // /WEB-INF/views/demo/demo.jsp
	}
	
	@RequestMapping("/demo/demo1.do")
	public String demo1(HttpServletRequest request) {
		String devName = request.getParameter("devName");
		int devAge = Integer.parseInt(request.getParameter("devAge"));
		String devEmail = request.getParameter("devEmail");
		String devGender = request.getParameter("devGender");
		String[] devLang = request.getParameterValues("devLang");

		Dev dev = new Dev(0, devName, devAge, devEmail, devGender, devLang);
		System.out.println("dev@demo1="+dev);

		request.setAttribute("dev", dev);

		return "demo/demoResult";
	}
	
	@RequestMapping("/demo/demo2.do")
	public String demo2(Model model,
						@RequestParam(value="devName", required=false) String devName,
						@RequestParam(value="devAge", defaultValue="20") int devAge,
						@RequestParam(value="devEmail") String devEmail,
						@RequestParam(value="devGender") String devGender,
						@RequestParam(value="devLang") String[] devLang) {
		
		Dev dev = new Dev(0, devName, devAge, devEmail, devGender, devLang);
		System.out.println("dev@demo2="+dev);
		
//		request.setAttribute("dev", dev);
		model.addAttribute("dev", dev);
		
		return "demo/demoResult";
	}
	
	@RequestMapping("/demo/demo3.do")
	public String demo3(Model model, Dev dev) {
		System.out.println("dev@demo3="+dev);
		model.addAttribute("dev", dev);
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/demo/insertDev.do", method=RequestMethod.POST)
	public String insertDev(Dev dev) {
		
		//업무로직
		int result = demoService.insertDev(dev);
		System.out.println(result>0?"등록성공":"등록실패");
		return "redirect:/";
		
	}
	
	@RequestMapping("/demo/demoList.do")
	public String selectDemoList(HttpServletRequest request) {
		List<Dev> list = demoService.selectDemoList();
		request.setAttribute("list", list);
		return "demo/demoList";
	}
	
	@RequestMapping("/demo/deleteDev.do")
	public String deleteDev(String no) {
		int result = demoService.deleteDev(no);
		System.out.println(result>0?"삭제성공":"삭제실패");
		return "redirect:/demo/demoList.do";
	}
	
}
