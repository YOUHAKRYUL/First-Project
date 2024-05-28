package com.itwill.sangbusamjo.shop.dao.sql;

public class MemberSQL {
	/*
	--회원가입
	insert into member(m_id,m_pw,m_name,m_phone,m_address) values('test5','5555','테스트5','010-5555-5555,','서울시 테스트구 5동');
	--로그인한회원의 정보보기(select pk)
	select * from member where m_id = 'test1';
	--로그인한회원의 회원의정보수정(update pk)
	update member set m_pw = '1234', m_name = '테스트1변경', m_phone='010-1234-1243',m_address='서울시 강남구 테스트동' where m_id = 'test1';
	--로그인한회원의 회원탈퇴(delete pk)
	delete member where m_id='test4';
	--아이디찾기
	select member.m_id from member where m_name = '테스트2' and m_phone = '010-2222-2222';
	--비밀번호찾기
	select member.m_pw from member where m_id = 'test2' and m_name = '테스트2';
	*/
	
	// 회원가입
	public static final String MEMBER_INSERT = "insert into member(m_id,m_pw,m_name,m_phone,m_address) values(?,?,?,?,?)";
	// 로그인한회원의 회원의정보수정(update pk)
	public static final String MEMBER_UPDATE = "update member set m_pw = ?, m_name = ?, m_phone=?,m_address=? where m_id = ?";
	// 로그인한회원의 회원탈퇴(delete pk)
	public static final String MEMBER_DELETE = "delete member where m_id=?";
	// 로그인한회원의 정보보기(select pk)
	public static final String MEMBER_SELECT_BY_ID = "select * from member where m_id = ?";
	// 회원리스트
	public static final String MEMBER_SELECT_ALL = "select * from member";
	// 아이디 찾기
	public static final String MEMBER_FIND_ID = "select member.m_id from member where m_name = ? and m_phone = ?";
	// 비밀번호 찾기
	public static final String MEMBER_FIND_PW = "select member.m_pw from member where m_id = ? and m_name = ?";
	// 인자로 전달되는 아이디를 가진 회원 존재여부 판별(회원아이디 중복확인 위한 SQL)
	public static final String USER_SELECT_BY_ID_COUNT= "select count(*) as cnt  from member where m_id=?";




}
