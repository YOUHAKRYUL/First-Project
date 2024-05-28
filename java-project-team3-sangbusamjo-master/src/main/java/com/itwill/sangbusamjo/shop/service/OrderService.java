package com.itwill.sangbusamjo.shop.service;

import java.util.ArrayList;

import java.util.List;

import com.itwill.sangbusamjo.shop.dao.CartDao;
import com.itwill.sangbusamjo.shop.dao.MemberDao;
import com.itwill.sangbusamjo.shop.dao.OrderDao;
import com.itwill.sangbusamjo.shop.dao.ProductDao;
import com.itwill.sangbusamjo.shop.vo.Cart;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;
import com.itwill.sangbusamjo.shop.vo.OrderItem;
import com.itwill.sangbusamjo.shop.vo.Product;

public class OrderService {

	private OrderDao orderDao;
	private ProductDao productDao;
	private CartDao cartDao;
	private MemberDao memberDao;

	public OrderService() throws Exception {
		orderDao = new OrderDao();
		productDao = new ProductDao();
		cartDao = new CartDao();
		memberDao = new MemberDao();
	}

	/*
	 * 주문1개 삭제
	 */
	public int deleteByOrderNo(int o_no) throws Exception {
		return orderDao.deleteByOrderNo(o_no);
	}

	/*
	 * 주문전체삭제
	 */
	public int deleteAll(String sUserId) throws Exception {
		return orderDao.deleteByUserId(sUserId);
	}

	/*
	 * 주문목록
	 */
	public List<Order> orderListAll(String sUserId) throws Exception {
		return orderDao.findOrderByUserId(sUserId);
	}

	/*
	 * 주문+주문아이템 목록
	 */
	public List<Order> orderWithOrderItemListAll(String sUserId) throws Exception {
		return orderDao.findOrderWithOrderItemByUserId(sUserId);
	}

	/*
	 * 주문+주문아이템 상세보기
	 */
	public Order orderWithOrderItem(int o_no) throws Exception {
		return orderDao.findByOrderNo(o_no);
	}

	/*
	 * 상품에서 직접주문 - TEST
	 */
	public int direct(String sUserId, int p_no, int oi_qty) throws Exception {

		Product product = productDao.findByPrimaryKey(p_no);
		Member member = memberDao.findByPk(sUserId);
		OrderItem orderItem = new OrderItem(0, oi_qty, null, product);
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderItemList.add(orderItem);

		Order newOrder = new Order(0, orderItemList.get(0).getOiQty() * orderItemList.get(0).getProduct().getPPrice(),null,
				null, null,null, null, orderItemList);

		return orderDao.insert(newOrder);
	}

	/*
	 * cart에서 주문
	 */
	
	public int createOrder(Order order) throws Exception {
		List<Cart> cartList = cartDao.findByUserId(order.getMember().getMId());
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		int o_tot_price = 0;
		int oi_tot_count = 0;
		for (Cart cart : cartList) {
			OrderItem orderItem = new OrderItem(0, cart.getCQty(), null, cart.getProduct());
			orderItemList.add(orderItem);
			o_tot_price += orderItem.getOiQty() * orderItem.getProduct().getPPrice();
			oi_tot_count += orderItem.getOiQty();
		}
		// String o_desc = orderItemList.get(0).getProduct().getPName()+"외
		// "+(oi_tot_count-1)+" 개";
		order.setOrderItemList(orderItemList);
		orderDao.insert(order);
		cartDao.cartListDeleteAll(order.getMember().getMId());
		return 0;
	}
	

	/*
	 * cart에서 선택주문 - TEST
	 */

	public int createChoice(String sUserId, String[] cart_item_noStr_array) throws Exception {

		Member member = memberDao.findByPk(sUserId);
		ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
		int o_tot_price = 0;
		int oi_tot_count = 0;
		for (int i = 0; i < cart_item_noStr_array.length; i++) {
			Cart cartItem = cartDao.findByCartNo(Integer.parseInt(cart_item_noStr_array[i]));
			OrderItem orderItem = new OrderItem(0, cartItem.getCQty(), null, cartItem.getProduct());
			orderItemList.add(orderItem);

			o_tot_price += orderItem.getOiQty() * orderItem.getProduct().getPPrice();
			oi_tot_count += orderItem.getOiQty();
		}

		// String o_desc = orderItemList.get(0).getProduct().getPName()+"외"+(oi_tot_count-1)+" 개";

		Order newOrder = new Order(0, o_tot_price, null, null,null, null,null, orderItemList);

		orderDao.insert(newOrder);

		for (int i = 0; i < cart_item_noStr_array.length; i++) {
			cartDao.cartListDelete(sUserId, Integer.parseInt(cart_item_noStr_array[i]));
		}

		return 0;
	}

}
