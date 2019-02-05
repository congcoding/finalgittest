package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

public interface BoardDao {

	List<Map<String, String>> selectBoardList(int cPage, int numPerPage);

	int totalContents();

	int insertBoard(Board board);

	int insertAttachment(Attachment a);

	Board boardView(int boardNo);

	List<Attachment> attachmentView(int boardNo);

}
