package com.kh.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoService {

	List<Memo> selectMemoList();

	int insertMemo(Map<String, String> map);

	int deleteMemo(Map<String, String> map);

}
