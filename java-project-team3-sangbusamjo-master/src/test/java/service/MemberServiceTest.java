package service;

import com.itwill.sangbusamjo.shop.service.MemberService;
import com.itwill.sangbusamjo.shop.vo.Member;

public class MemberServiceTest {

	public static void main(String[] args)throws Exception {
		MemberService memberService = new MemberService();

		/*
		  0  : 실패
		  1  : 성공
		 */

		/*
		 * System.out.println("1. 가입"); int rowCount = memberService.create(new
		 * Member("test10","1010","테스트10","010-1010-1010","서울시 강남구 테스트동10"));
		 * System.out.println(rowCount);
		 * 
		 * System.out.println("2. 중복체크");
		 * System.out.println("Duplicate : "+memberService.isDuplicate("test1"));
		 */
		System.out.println("3. 로그인");
		int result1 = memberService.login("test1", "1111");
		System.out.println("결과 : "+result1);
		int result2 = memberService.login("test2", "2222");
		System.out.println("결과 : "+result2);
		/*
		 * result = memberService.login("test14", "1111"); // 아이디 틀림
		 * System.out.println("결과 : "+result); result = memberService.login("test1",
		 * "41111"); // 비밀번호 틀림 System.out.println("결과 : "+result);
		 */

		/*
		 * System.out.println("4. 수정"); rowCount = memberService.update(new
		 * Member("test2","222222","업데이트테스트","010-2212-2212","서울시 업데이트"));
		 * System.out.println("결과 : "+rowCount );
		 * System.out.println(memberService.findMember("test2"));
		 * 
		 * System.out.println("5. 삭제"); rowCount = memberService.delete("test5");
		 * System.out.println("결과 : "+rowCount);
		 * 
		 * System.out.println("6. 한명의 회원 정보보기 : \n" +
		 * memberService.findMember("test3"));
		 * 
		 * System.out.println("7. 아이디찾기"); Member memberId =
		 * memberService.findId("테스트1", "010-1111-1111");
		 * System.out.println("결과 : "+memberId); System.out.println("8. 비밀번호찾기"); Member
		 * memberPw = memberService.findPw("test1","테스트1");
		 * System.out.println("결과 : "+memberPw);
		 */
	}

}
