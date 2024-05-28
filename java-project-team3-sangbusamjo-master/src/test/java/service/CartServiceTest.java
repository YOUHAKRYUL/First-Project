package service;
import java.util.List;

import com.itwill.sangbusamjo.shop.service.CartService;
import com.itwill.sangbusamjo.shop.vo.Cart;

public class CartServiceTest {

	public static void main(String[] args) throws Exception {
		CartService cartService = new CartService();
		
		//카트리스트 보기
		//List<Cart> cartList1 = cartService.getCartItemByUserId("test1");
		//System.out.println("test1-->" + cartList1);
		
		//카트추가 ............
		int rowCount = cartService.addCart(100, 1, "test1");
		System.out.println(rowCount);
		
		/*
		// 수량 수정
		int rowCount1 = cartService.updateCart(3, "yhr1", 3);
		System.out.println(rowCount1);

		// 수량증가
		int rowCount2 = cartService.CartItemQtyUpdateU("test1", 1);
		System.out.println(rowCount2);

		//수량감소
		int rowCount3 = cartService.CartItemQtyUpdateD("yhr1", 1);
		System.out.println(rowCount3);
		//카트 전체삭제
		int rowCount4 = cartService.deleteCartItemByUserId("yhr1");
		System.out.println(rowCount4);
		//카트 선택삭제
		int rowCount5 = cartService.deleteCartItemByCartNo("test1",3);
		System.out.println(rowCount5);
		*/
		
	}
}