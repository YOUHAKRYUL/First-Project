package com.itwill.sangbusamjo.shop.service;

import com.itwill.sangbusamjo.shop.dao.MemberDao;
import com.itwill.sangbusamjo.shop.vo.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() throws Exception {
		memberDao = new MemberDao();
	}

	// 회원가입
	public int create(Member member) throws Exception {
		// 1. 아이디 중복 체크
		if (memberDao.countByMemberId(member.getMId()) >= 1) {
			// 중복
			return -1;
		} else {
			// 가입
			int rowCount = memberDao.insert(member);
			return rowCount;
		}

	}

	//회원 로그인
	public int login(String mId, String mPw) throws Exception{
		/*
		 	0 : 실패
		 	1 : 성공
		 */
		
		int result = 0;
		if (memberDao.countByMemberId(mId)==1) {
			//아이디 존재하는경우
			Member memberLogin = memberDao.findByPk(mId);
			if (memberLogin.getMPw().equals(mPw)) {
				//비밀번호 일치
				result = 1;
			} else {
				//비밀번호 불일치
				result = 0;
			}
		} else {
			//비회원
			result = 0;
		}
	return result;
	}

	//회원 로그아웃

	//로그인 한 회원정보보기
	public Member findMember(String mId)throws Exception {

		return memberDao.findByPk(mId);
	}
	//회원 수정
	public int update(Member member)throws Exception {

		return memberDao.update(member);
	}
	//아이디 중복체크
	public boolean isDuplicate(String mId) throws Exception{
		if (memberDao.countByMemberId(mId)>=1) {
			return true;
		} else {
			return false;
		}

	}
	//회원탈퇴
	public int delete(String mId)throws Exception {
		return memberDao.delete(mId);
	}

	//아이디 찾기
	public Member findId(String mName, String mPhone)throws Exception {

		return memberDao.findById(mName, mPhone);
	}
	//비밀번호찾기
	public Member findPw(String mId, String mName)throws Exception {

		return memberDao.findByPw(mId,mName);
	}
}
