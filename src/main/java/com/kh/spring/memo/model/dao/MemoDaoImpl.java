package com.kh.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<Memo> selectMemoList() {
		return sqlSession.selectList("memo.selectMemoList");
	}

	@Override
	public int insertMemo(Map<String, String> map) {
		return sqlSession.insert("memo.insertMemo", map);
	}

	@Override
	public int deleteMemo(Map<String, String> map) {
		return sqlSession.delete("memo.deleteMemo", map);
	}

}
