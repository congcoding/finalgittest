package com.kh.spring.memo.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Memo implements Serializable{

	private static final long serialVersionUID = 1L;

	private int memoNo;
	private String memo;
	private String password;
	private Date memoDate;
	
	public Memo() {}
	
	public Memo(int memoNo, String memo, String password, Date memoDate) {
		this.memoNo = memoNo;
		this.memo = memo;
		this.password = password;
		this.memoDate = memoDate;
	}

	public int getMemoNo() {
		return memoNo;
	}
	public void setMemoNo(int memoNo) {
		this.memoNo = memoNo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getMemoDate() {
		return memoDate;
	}
	public void setMemoDate(Date memoDate) {
		this.memoDate = memoDate;
	}
	
	@Override
	public String toString() {
		return "[memoNo=" + memoNo + ", memo=" + memo + ", password=" + password + ", memoDate=" + memoDate + "]";
	}

}