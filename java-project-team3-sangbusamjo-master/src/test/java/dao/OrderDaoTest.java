package dao;

import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.dao.OrderDao;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;
import com.itwill.sangbusamjo.shop.vo.OrderItem;
import com.itwill.sangbusamjo.shop.vo.Product;

public class OrderDaoTest {

	public static void main(String[] args) throws Exception {

		OrderDao orderDao = new OrderDao();
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		OrderItem orderitem1 = new OrderItem(99, 10, Order.builder().oNo(1).build(), Product.builder().pNo(1).build());
		OrderItem orderitem2 = new OrderItem(98, 10, Order.builder().oNo(2).build(), Product.builder().pNo(2).build());
		OrderItem orderitem3 = new OrderItem(97, 10, Order.builder().oNo(3).build(), Product.builder().pNo(3).build());

		orderItemList.add(orderitem1);
		orderItemList.add(orderitem2);
		orderItemList.add(orderitem3);

		// Insert - 주문 추가
		Order addOrder = new Order(99, 1000, "집으로", "서울", "010-1111-1111", null,null,
				new Member("test1", "1111", "테스트1", "test99", "test99"), orderItemList);
		int rowCount = 0;
		rowCount = orderDao.insert(addOrder);
		System.out.println(">> " + rowCount);

		// All delete - 주문 전체 삭제
		rowCount = orderDao.deleteByUserId("test2");
		System.out.println(">> " + rowCount);

		// OrderNo delete - 주문 1건 삭제
		rowCount = orderDao.deleteByOrderNo(38);
		System.out.println(">> " + rowCount);

		// OrderList All - 주문 전체리스트
		List<Order> orderList = orderDao.findOrderByUserId("test1");
		System.out.println(">> " + orderList);

		// OrderList [+ OrderItem] - 주문+주문아이템 전체리스트
		List<Order> orderList1 = orderDao.findOrderWithOrderItemByUserId("test1");
		System.out.println(">> " + orderList1);

		// Order No - 주문+주문아이템 한개
		Order orderNo = orderDao.findByOrderNo(45);
		System.out.println(">> " + orderNo);

		// OrderList [+ OrderItem]- 주문+주문아이템 전체리스트 (날짜순으로 내림차순 정렬)
		List<Order> orderList2 = orderDao.findOrderWithOrderItemByUserIdByDateDesc("test1");
		System.out.println(">> " + orderList2);

		// OrderList [+ OrderItem]- 주문+주문아이템 전체리스트 (가격순으로 내림차순 정렬)
		orderList = orderDao.findOrderWithOrderItemByUserIdByTotalPriceDesc("test1");
		System.out.println(">> 가격순으로 내림차순\n" + orderList);

	}

}
