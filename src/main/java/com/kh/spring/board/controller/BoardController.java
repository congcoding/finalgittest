package com.kh.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.util.Utils;

@Controller
public class BoardController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView selectBoardList(@RequestParam(value="cPage", defaultValue="1") int cPage,
										ModelAndView mav) {
		if(logger.isDebugEnabled()) {
			logger.debug("게시판 목록 페이지");
		}
		
		int numPerPage = 10;
		
		//업무로직
		//1. 게시글리스트(페이징 적용)
		List<Map<String, String>> list = boardService.selectBoardList(cPage, numPerPage);
		logger.debug("list="+list);
		
		//2. 전체컨텐츠 수
		int totalContents = boardService.totalContents();
		
		mav.addObject("list", list);
		mav.addObject("totalContents", totalContents);
		mav.addObject("numPerPage", numPerPage);
		mav.addObject("cPage", cPage);
		mav.setViewName("board/boardList");
		return mav;
	}
	
	@RequestMapping("/board/boardForm.do")
	public void boardForm() {
		//return type이 void일 때 : ViewTranslator에서 요청url로부터 뷰단파일경로를 유추
//		return "board/boardForm";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public ModelAndView insertBoard(Board board, @RequestParam(name="upFile", required=false) MultipartFile[] upFiles, HttpServletRequest request, ModelAndView mav) {
		logger.debug("board="+board);
		logger.debug("fileName1="+upFiles[0].getOriginalFilename());
		logger.debug("size1="+upFiles[0].getSize());
		logger.debug("fileName2="+upFiles[1].getOriginalFilename());
		logger.debug("size2="+upFiles[1].getSize());
		
		try {
		
			//1. 파일업로드
			String saveDirectory = request.getSession().getServletContext().getRealPath("/resources/upload/board");
			logger.debug(saveDirectory);
			
			List<Attachment> attachList = new ArrayList<>();
			
			//MultipartFile처리
			for(MultipartFile f : upFiles) {
				if(!f.isEmpty()) {
					//파일명(업로드)
					String originalFileName = f.getOriginalFilename();
					
					//파일명(서버저장용)
					String renamedFileName = Utils.getRenamedFileName(originalFileName);
					
					logger.debug("renamedFileName="+renamedFileName);
					
					//실제서버에 파일저장
					try {
						f.transferTo(new File(saveDirectory+"/"+renamedFileName));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
					
					//첨부파일객체 생성. 리스트 추가
					Attachment attach = new Attachment();
					attach.setOriginalFileName(originalFileName);
					attach.setRenamedFileName(renamedFileName);
					attachList.add(attach);
				}
			}
			
			//2. 업무로직
			int result = boardService.insertBoard(board, attachList);
			
			//3. 뷰단처리
			String loc = "/board/boardList.do";
			String msg = "";
			if(result>0) {
				msg = "게시물 등록 성공!";
			}
			else {
				msg = "게시물 등록 실패!";
			}
			
			mav.addObject("msg", msg);
			mav.addObject("loc", loc);
			mav.setViewName("common/msg");
		} catch(Exception e) {
			logger.error("게시물 등록 에러", e);
			throw new BoardException("게시물 등록 에러", e);
		}
		
		return mav;
	}
	
	@RequestMapping("/board/boardView.do")
	public String boardView(@RequestParam int boardNo, Model model) {
		Board board = boardService.boardView(boardNo);
		List<Attachment> attachmentList = boardService.attachmentView(boardNo);

		model.addAttribute("board", board);
		model.addAttribute("attachmentList", attachmentList);
		return "board/boardView";
	}
	
	@RequestMapping("/board/fileDownload.do")
	public void fileDownload(@RequestParam String rName, @RequestParam String oName, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		//파일 입출력 준비
		String saveDirectory = request.getSession().getServletContext().getRealPath("/resources/upload/board");
		
		//입력 스트림
		File f = new File(saveDirectory+"/"+rName);
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		//출력스트림
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		//전송할 파일명작성
		String resFileName = "";
		
		//요청브라우저에 따른 분기를 처리
		boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1 //요청브라우저를 가져오는 key
						|| request.getHeader("user-agent").indexOf("Trident") != -1; //IE10 이전(MSIE), 이후(Trident)
		if(isMSIE) {
			//utf-8인코딩처리를 명시적으로 해줌
			resFileName = URLEncoder.encode(oName, "UTF-8");
			//+로 처리된 공백을 다시 한 번 %20(공백의미)로 치환
			resFileName = resFileName.replaceAll("\\+", "%20");
		}else {
			resFileName = new String(oName.getBytes("UTF-8"), "ISO-8859-1"); //톰캣 기본 인코딩타입			
		}
		
		//파일전송
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFileName);
		
		//파일쓰기
		int read = -1;
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}
}
