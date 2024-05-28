package dao;

import com.itwill.sangbusamjo.shop.dao.MemberDao;
import com.itwill.sangbusamjo.shop.vo.Member;

public class MemberDaoTest {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao = new MemberDao();
		/*
		 * Member insertTest = new
		 * Member("test20","2020","dao테스트","010-2020-2020","테스트주소"); Member updateTest =
		 * new Member("test20","222424","dao테스트업데이트","010-2020-2020","테스트주소");
		 * 
		 * //System.out.println("1.insert : "+memberDao.insert(insertTest));
		 * System.out.println("2.update : "+memberDao.update(updateTest));
		 * System.out.println("3.delete : "+memberDao.delete("yhr3"));
		 * System.out.println("4.findByPk : "+memberDao.findByPk("test1"));
		 * System.out.println("5.findAll : "+memberDao.findAll());
		 */
		System.out.println("6.countByMemberId : "+memberDao.countByMemberId("test11"));
		System.out.println("6.countByMemberId : "+memberDao.countByMemberId("test2"));
		/*
		 * System.out.println("7.findById : "+memberDao.findById("테스트1",
		 * "010-1111-1111"));
		 * System.out.println("8.findByPw : "+memberDao.findBy	Pw("test1","테스트1"));
		 */
	}
}
