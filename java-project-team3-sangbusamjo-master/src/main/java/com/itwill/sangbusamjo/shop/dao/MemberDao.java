package com.itwill.sangbusamjo.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.common.DataSource;
import com.itwill.sangbusamjo.shop.dao.sql.MemberSQL;
import com.itwill.sangbusamjo.shop.vo.Member;

public class MemberDao {
	private DataSource dataSource;

	public MemberDao() throws Exception {
		dataSource = new DataSource();
	}

	// member 테이블에 회원 생성
	public int insert(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_INSERT);

		pstmt.setString(1, member.getMId());
		pstmt.setString(2, member.getMPw());
		pstmt.setString(3, member.getMName());
		pstmt.setString(4, member.getMPhone());
		pstmt.setString(5, member.getMAddress());

		int rowCount = pstmt.executeUpdate();

		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}

	// 회원 아이디에 해당하는 회원 삭제
	public int delete(String mId) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_DELETE);
		pstmt.setString(1, mId);

		int rowCount = pstmt.executeUpdate();

		pstmt.close();
		dataSource.close(con);
		return rowCount;

	}

	// 회원 정보 수정
	public int update(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_UPDATE);

		pstmt.setString(1, member.getMPw());
		pstmt.setString(2, member.getMName());
		pstmt.setString(3, member.getMPhone());
		pstmt.setString(4, member.getMAddress());
		pstmt.setString(5, member.getMId());
		
		int rowCount = pstmt.executeUpdate();

		pstmt.close();
		dataSource.close(con);
		return rowCount;

	}

	// 아이디 찾기
	public Member findById(String mName, String mPhone) throws Exception {
		Member member = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_ID);
		pstmt.setString(1, mName);
		pstmt.setString(2, mPhone);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			member = new Member(rs.getString("m_id"));
			//member.setMId(rs.getString("m_id"));
		}

		rs.close();
		pstmt.close();
		dataSource.close(con);
		return member;
	}

	// 비밀번호 찾기
	public Member findByPw(String mId, String mName) throws Exception {
		Member member = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_FIND_PW);
		pstmt.setString(1, mId);
		pstmt.setString(2, mName);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			//member = new Member(rs.getString("m_pw"));
			member = new Member();
			member.setMPw(rs.getString("m_pw"));

		}

		rs.close();
		pstmt.close();
		dataSource.close(con);
		return member;
	}

	// 회원 아이디에 해당하는 정보를 DB에서 1개 찾아서 member객체로 반환
	public Member findByPk(String mId) throws Exception {
		Member member = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_ID);
		pstmt.setString(1, mId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			member = new Member(rs.getString("m_id"),
								rs.getString("m_pw"),
								rs.getString("m_name"),
								rs.getString("m_phone"),
								rs.getString("m_address")

			);
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return member;
	}

	// 회원 리스트
	public List<Member> findAll() throws Exception {
		List<Member> memberList = new ArrayList<>();
		Member member = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			memberList.add(new Member(
					rs.getString("m_id"),
					rs.getString("m_pw"),
					rs.getString("m_name"),
					rs.getString("m_phone"),
					rs.getString("m_address")

			));
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return memberList;
	}

	// 인자로 전달되는 아이디를 가진 회원 존재여부 판별
	public int countByMemberId(String mId) throws Exception {
		
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.USER_SELECT_BY_ID_COUNT);
		pstmt.setString(1, mId);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int memberCount = rs.getInt(1);
		//System.out.println(memberCount);
		rs.close();
		pstmt.close();
		dataSource.close(con);

		return memberCount;
	}

}
