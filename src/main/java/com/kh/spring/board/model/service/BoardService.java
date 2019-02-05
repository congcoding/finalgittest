package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardService {

	List<Map<String, String>> selectBoardList(int cPage, int numPerPage);

	int totalContents();

	int insertBoard(Board board, List<Attachment> attachList);

	Board boardView(int boardNo);

	List<Attachment> attachmentView(int boardNo);

}
