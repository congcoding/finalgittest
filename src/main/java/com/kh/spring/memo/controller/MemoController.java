package com.kh.spring.memo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

@Controller
public class MemoController {
	
	@Autowired
	MemoService memoService;
	
	@RequestMapping("/memo/memo.do")
	public ModelAndView memo(ModelAndView mav) {
		List<Memo> list = memoService.selectMemoList();
		mav.addObject("list", list);
		mav.setViewName("memo/memo");
		return mav;
	}
	
	/*
	@RequestMapping("/memo/insertMemo.do")
	public String insertMemo(Memo memo) {
		int result = memoService.insertMemo(memo);
		return "redirect:/memo/memo.do";
	}
	*/
	
	@RequestMapping("/memo/insertMemo.do")
	public String insertMemo(@RequestParam String memo, @RequestParam String password){
		Map<String, String> map = new HashMap<>(); //spring 기본 jdk 1.6이므로 제네릭 생략 불가 -> 1.8로 업데이트 세팅해주면 생략 가능
		map.put("memo", memo);
		map.put("password", password);
		memoService.insertMemo(map);
		
		return "redirect:/memo/memo.do";
	}
	
	@RequestMapping("/memo/deleteMemo.do")
	public String deleteMemo(@RequestParam String no, @RequestParam String password, Model model) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("memoNo", no);
		map.put("password", password);
		
		int result = memoService.deleteMemo(map);
		
		if(result > 0) {
			model.addAttribute("msg", "메모 삭제 성공!");		
		}
		else {
			model.addAttribute("msg", "비밀번호가 틀렸습니다.");
		}
		model.addAttribute("loc", "/memo/memo.do");
		
		return "common/msg";
	}
	
	
}
