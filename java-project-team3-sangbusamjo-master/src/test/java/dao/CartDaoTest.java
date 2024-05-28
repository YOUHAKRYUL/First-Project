package dao;

import java.util.List;

import com.itwill.sangbusamjo.shop.dao.CartDao;
import com.itwill.sangbusamjo.shop.vo.Cart;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Product;

public class CartDaoTest {
	public static void main(String[] args) throws Exception {
		CartDao cartDao = new CartDao();
		/*
		 * System.out.println("1.add(insert)"); Cart addCart=new Cart(4,1,"yhr2", new
		 * Product(4,"미니멀 라운드 니트", "기타 상세 정보 등...", "니트.png",39000, 1) ); int
		 * rowCount=1;
		 */
		/*
		 * System.out.println(cartDao.countByProductNo(cartDao.updateByProductNo(Cart.
		 * builder() .user(User.builder().userid("guard1").build())
		 * .product(Product.builder().pNo(1).build()) .cartQty(8) .build())));
		 */
		List<Cart> cartList = cartDao.findByUserId("yhr1");
		System.out.println("yhr1-->" + cartList);
		System.out.println(cartDao.findByUserId("test1"));
		System.out.println("로그인한 멤버의 카트 리스트 전체삭제 : " + cartDao.cartListDeleteAll("yhr2"));
		System.out.println("로그인한 멤버의 카트 리스트 선택삭제 : " + cartDao.cartListDelete("test1", 1));
		System.out.println("로그인한 멤버의 카트 1개 아이템 수량 변경 : " + cartDao.cartQtyUpdate(10, "test1", 2));
		System.out.println("로그인한 멤버의 카트에 존재하는 제품수량증가 : " + cartDao.CartQtyUpdateU("test1", 1));
		System.out.println("로그인한 멤버의 카트에 존재하는 제품수량감소 : " + cartDao.CartQtyUpdateD("test1", 1));
		System.out.println("로그인한 멤버의 카트에 존재하는 제품의 수 : " + cartDao.countByProductNo("test1", 1));
		System.out.println("로그인한 멤버의 카트 번호로 아이템 출력 : " + cartDao.findByCartNo(2));
		System.out.println("로그인한 멤버의 카트 번호로 카트리스트 삭제 : " + cartDao.cartDeleteByNo(8));

		// 리스트
	}
}