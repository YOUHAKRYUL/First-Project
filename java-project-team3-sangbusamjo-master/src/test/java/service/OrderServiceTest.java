package service;

import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.service.OrderService;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;
import com.itwill.sangbusamjo.shop.vo.OrderItem;
import com.itwill.sangbusamjo.shop.vo.Product;

public class OrderServiceTest {

	public static void main(String[] args) throws Exception {

		OrderService orderService = new OrderService();

		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		OrderItem orderitem1 = new OrderItem(99, 10, Order.builder().oNo(1).build(), Product.builder().pNo(1).build());
		OrderItem orderitem2 = new OrderItem(98, 10, Order.builder().oNo(2).build(), Product.builder().pNo(2).build());
		OrderItem orderitem3 = new OrderItem(97, 10, Order.builder().oNo(3).build(), Product.builder().pNo(3).build());

		orderItemList.add(orderitem1);
		orderItemList.add(orderitem2);
		orderItemList.add(orderitem3);
		

		Order addOrder = new Order(99, 1000, "집으로", "서울", "010-1111-1111", null,null,
				new Member("test1", "1111", "테스트1", "test99", "test99"), orderItemList);

		int rowCount = 0;
/*
		// 주문1개 삭제

		rowCount = orderService.deleteByOrderNo(11);
		System.out.println(">> " + rowCount);

		// 주문전체삭제
		rowCount = orderService.deleteAll("test1");
		System.out.println(">> " + rowCount);

		// 주문목록
		List<Order> orderList = orderService.orderListAll("yhr1");
		System.out.println(orderList);

		// 주문+주문아이템 목록
		List<Order> orderList1 = orderService.orderWithOrderItemListAll("yhr1");
		System.out.println(orderList1);

		// 주문+주문아이템 상세보기
		Order order = orderService.orderWithOrderItem(3);
		System.out.println(order);

		// 상품에서 직접주문 - TEST O -> 불확실 ㅠㅠ
		rowCount = orderService.direct("test3", 1, 10);
		System.out.println(rowCount);
*/
		// cart에서 주문
		//Order order3 = new Order(0, 0, null, null, null, null, orderItemList);
				orderService.createOrder(addOrder);
		//System.out.println(order3);
		
		/* cart에서 선택주문 - TEST X
		rowCount = orderService.createChoice("test1", args);
	    System.out.println(rowCount);
	    */

	}

}
