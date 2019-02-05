package com.kh.spring.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.vo.Dev;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	DemoDao demoDao;

	@Override
	public int insertDev(Dev dev) {
		return demoDao.insertDev(dev);
	}

	@Override
	public List<Dev> selectDemoList() {
		return demoDao.selectDemoList();
	}

	@Override
	public int deleteDev(String no) {
		return demoDao.deleteDev(no);
	}
	
}
