package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member m);

	Member selectOneMember(String memberId);

	Member memberView(String memberId);

	int updateMember(Member m);

}
