package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller
@SessionAttributes(value= {"memberLoggedIn"})
public class MemberController {
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@RequestMapping("/member/memberEnroll.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String insertMember(Member m, HttpServletRequest req) {

		//1. 비즈니스로직 실행
		System.out.println("암호화전 : "+m.getPassword());
		String temp = m.getPassword();
		
		//BCrypte방식 암호화
		m.setPassword(bcryptPasswordEncoder.encode(temp));
		System.out.println("암호화후 : "+m.getPassword());
		int result = memberService.insertMember(m);
		
		//2. 처리결과에 따른 view단 분기처리
		String loc = "/";
		String msg = "";
		if(result>0) msg="회원가입성공!";
		else msg="회원가입실패!";
		req.setAttribute("loc", loc);
		req.setAttribute("msg", msg);
		return "common/msg";
		
	}

/**
	@RequestMapping(value="/member/memberLogin.do", method=RequestMethod.POST)
	public String memberLogin(@RequestParam String memberId, @RequestParam String password, Model model, HttpSession session) {
//		selectOne메소드 호출결과  Member객체를 가져오기
		Member m = memberService.selectOneMember(memberId);
		
		String msg = "";
		String loc = "";
		String view = "common/msg";
		
		//로그인처리
		if(m == null) {
			msg = "아이디가 존재하지 않습니다.";
			loc = "/";
		}
		else {
			if(bcryptPasswordEncoder.matches(password, m.getPassword())) {
				//세션 - 상태유지
//				session.setAttribute("memberLoggedIn", m);
				model.addAttribute("memberLoggedIn", m);
				view = "redirect:/";
			}
			else {
				msg = "비밀번호가 틀렸습니다.";
				loc = "/";
			}
		}
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		return view;
	}
**/
	
	/**
	 * ModelAndView(2.0)
	 *  - Model과 view단 정보를 하나의 객체에서 관리
	 * ModelMap(2.0) : 일반클래스
	 *  - Model객체관리, view단은 문자열로 리턴
	 * Model(2.5) : 인터페이스
	 *  - Model객체관리, view단은 문자열로 리턴
	 */
	@RequestMapping(value="/member/memberLogin.do", method=RequestMethod.POST)
	public ModelAndView memberLogin(@RequestParam String memberId, @RequestParam String password, ModelAndView mav, HttpSession session) {
//		selectOne메소드 호출결과  Member객체를 가져오기
		Member m = memberService.selectOneMember(memberId);
		
		String msg = "";
		String loc = "";
		String view = "common/msg";
		
		//로그인처리
		if(m == null) {
			msg = "아이디가 존재하지 않습니다.";
			loc = "/";
		}
		else {
			if(bcryptPasswordEncoder.matches(password, m.getPassword())) {
				//세션 - 상태유지
//				session.setAttribute("memberLoggedIn", m);
				mav.addObject("memberLoggedIn", m);
				view = "redirect:/";
			}
			else {
				msg = "비밀번호가 틀렸습니다.";
				loc = "/";
			}
		}
		mav.addObject("loc", loc);
		mav.addObject("msg", msg);
		mav.setViewName(view);
		return mav;
	}
	
	@RequestMapping("/member/memberLogout.do")
	public String logout(SessionStatus sessionStatus) {
		//session.setAttribute() -> session.invalidate();
		//@SessionAttributes -> sessionStatus.setComplete();
		if(!sessionStatus.isComplete()) {
			sessionStatus.setComplete();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/member/memberView.do")
	public String memberView(@RequestParam String memberId, Model model) {
		if(logger.isDebugEnabled())
			logger.debug("회원정보보기 요청!");
			
		Member m = memberService.memberView(memberId);
		model.addAttribute("m", m);

		return "member/memberView";
	}
	
	@RequestMapping("/member/memberUpdateEnd.do")
	public String memberUpdateEnd(Member m, Model model) {
		
		int result = memberService.updateMember(m);
		
		model.addAttribute("m", m);
		model.addAttribute("memberLoggedIn", m);
		model.addAttribute("msg", "회원정보 수정 성공");
		model.addAttribute("loc", "/member/memberView.do?memberId="+m.getMemberId());
		return "common/msg";
	}
	
}
