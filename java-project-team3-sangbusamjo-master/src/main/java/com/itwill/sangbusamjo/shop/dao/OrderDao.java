package com.itwill.sangbusamjo.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.sangbusamjo.shop.common.DataSource;
import com.itwill.sangbusamjo.shop.dao.sql.OrderSQL;
import com.itwill.sangbusamjo.shop.vo.Member;
import com.itwill.sangbusamjo.shop.vo.Order;
import com.itwill.sangbusamjo.shop.vo.OrderItem;
import com.itwill.sangbusamjo.shop.vo.Product;

public class OrderDao {

	private DataSource dataSource;

	public OrderDao() throws Exception {
		dataSource = new DataSource();
	}

	/*
	 * 주문전체삭제(ON DELETE CASCADE)
	 */
	public int deleteByUserId(String sUserId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_ID);
			pstmt.setString(1, sUserId);
			rowCount = pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}

	/*
	 * 주문1건삭제(ON DELETE CASCADE)
	 */
	public int deleteByOrderNo(int oNo) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_NO);
			/*
			 * "delete from orders where o_no = ?"
			 */
			pstmt.setInt(1, oNo);
			rowCount = pstmt.executeUpdate();
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return rowCount;
	}

	/*
	 * 주문생성
	 */
	public int insert(Order order) throws Exception {

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_INSERT);
			/*
			 * INSERT INTO Orders (o_no, o_totalprice, o_comment, o_address, o_phone,
			 * o_date, m_id) VALUES (Orders_o_no_seq.nextval, ?, ?, ?, ?, sysdate, ?)"
			 */
			pstmt1.setInt(1, order.getOTotalPrice());
			pstmt1.setString(2, order.getOComment());
			pstmt1.setString(3, order.getOAddress());
			pstmt1.setString(4, order.getOPhone());
			pstmt1.setString(5, order.getMember().getMId());
			pstmt1.setString(6, order.getOName());

			pstmt1.executeUpdate();
			pstmt2 = con.prepareStatement(OrderSQL.ORDERITEM_INSERT);
			/*
			 * "INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES
			 * (Order_Item_oi_no_seq.nextval, ?, Orders_o_no_seq.currval, ?)"
			 */
			for (OrderItem orderItem : order.getOrderItemList()) {
				pstmt2.setInt(1, orderItem.getOiQty());
				pstmt2.setInt(2, orderItem.getProduct().getPNo());
				pstmt2.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return 0;
	}

	/*
	 * 주문전체(특정사용자)
	 */
	public List<Order> findOrderByUserId(String sUserId) throws Exception {
		ArrayList<Order> orderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			/*
			 * "select * from orders where m_id = ?"
			 */
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_ID_BY_DATE_DESC);
			pstmt.setString(1, sUserId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderList.add(Order.builder().oNo(rs.getInt("o_no")).oTotalPrice(rs.getInt("o_totalprice"))
						.oComment(rs.getString("o_comment")).oAddress(rs.getString("o_address"))
						.oPhone(rs.getString("o_phone")).oDate(rs.getDate("o_date"))
						.member(Member.builder().mId(rs.getString("m_id")).build())
						.oName(rs.getString("o_name"))
						.build());
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}

	/*
	 * 주문+주문아이템 전체(특정사용자)
	 */
	public List<Order> findOrderWithOrderItemByUserId(String sUserId) throws Exception {

		List<Order> orderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = dataSource.getConnection();
			/*
			 * "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = ?"
			 */
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_ID);
			pstmt1.setString(1, sUserId);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				orderList.add(Order.builder().oNo(rs1.getInt("o_no")).oTotalPrice(rs1.getInt("o_totalprice"))
						.oComment(rs1.getString("o_comment")).oAddress(rs1.getString("o_address"))
						.oPhone(rs1.getString("o_phone")).oDate(rs1.getDate("o_date"))
						.member(Member.builder().mId(rs1.getString("M_id")).build()).build());
			}
			pstmt2 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);
			/*
			 * "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.o_no = ?"
			 */
			for (int i = 0; i < orderList.size(); i++) {
				Order tempOrder = orderList.get(i);
				pstmt2.setInt(1, tempOrder.getONo());
				rs2 = pstmt2.executeQuery();
				Order orderWithOrderItem = null;
				if (rs2.next()) {
					orderWithOrderItem = Order.builder().oNo(rs2.getInt("o_No")).oTotalPrice(rs2.getInt("o_TotalPrice"))
							.oComment(rs2.getString("O_Comment")).oAddress(rs2.getString("O_Address"))
							.oPhone(rs2.getString("O_Phone")).oDate(rs2.getDate("O_Date"))
							.member(Member.builder().mId(rs2.getString("M_id")).build()).build();
					do {
						OrderItem orderWithOrderItemList = OrderItem.builder().OiNo(rs2.getInt("oi_NO"))
								.OiQty(rs2.getInt("oi_qty")).order(Order.builder().oNo(rs2.getInt("O_NO")).build())
								.product(Product.builder().pNo(rs2.getInt("P_NO")).build()).build();
						orderWithOrderItem.getOrderItemList().add(orderWithOrderItemList);
					} while (rs2.next());
				}
				orderList.set(i, orderWithOrderItem);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}

	/*
	 * 주문+주문아이템 한개
	 */
	public Order findByOrderNo(int oNo) throws Exception {

		Order order = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();

			if (rs.next()) { 

				order = Order.builder().oNo(rs.getInt("o_No")).oTotalPrice(rs.getInt("o_TotalPrice"))
						.oComment(rs.getString("O_Comment")).oAddress(rs.getString("O_Address"))
						.oPhone(rs.getString("O_Phone")).oDate(rs.getDate("O_Date"))
						.member(Member.builder().mId(rs.getString("M_id")).build()).build();
				do {
					OrderItem orderWithOrderItemList = OrderItem.builder().OiNo(rs.getInt("oi_NO"))
							.OiQty(rs.getInt("oi_qty")).order(Order.builder().oNo(rs.getInt("O_NO")).build())
							.product(Product.builder().pNo(rs.getInt("P_NO")).pDesc(rs.getString("P_desc"))
									.pImage(rs.getString("P_image")).pName(rs.getString("p_name"))
									.pPrice(rs.getInt("p_price")).pCategory(rs.getInt("p_category")).build())
							.build();
					order.getOrderItemList().add(orderWithOrderItemList);
				} while (rs.next());
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return order;
	}

	/*
	 * 주문 날짜 순 내림차순으로 검색 가능
	 */
	public List<Order> findOrderWithOrderItemByUserIdByDateDesc(String sUserId) throws Exception {

		List<Order> orderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = dataSource.getConnection();
			/*
			 * "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = ? order by o_date DESC"
			 */
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_ID_BY_DATE_DESC);
			pstmt1.setString(1, sUserId);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				orderList.add(Order.builder().oNo(rs1.getInt("o_no")).oTotalPrice(rs1.getInt("o_totalprice"))
						.oComment(rs1.getString("o_comment")).oAddress(rs1.getString("o_address"))
						.oPhone(rs1.getString("o_phone")).oDate(rs1.getDate("o_date"))
						.member(Member.builder().mId(rs1.getString("M_id")).build()).build());
			}
			pstmt2 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);

			for (int i = 0; i < orderList.size(); i++) {
				Order tempOrder = orderList.get(i);
				pstmt2.setInt(1, tempOrder.getONo());
				rs2 = pstmt2.executeQuery();
				Order orderWithOrderItem = null;
				if (rs2.next()) {
					orderWithOrderItem = Order.builder().oNo(rs2.getInt("o_No")).oTotalPrice(rs2.getInt("o_TotalPrice"))
							.oComment(rs2.getString("O_Comment")).oAddress(rs2.getString("O_Address"))
							.oPhone(rs2.getString("O_Phone")).oDate(rs2.getDate("O_Date"))
							.member(Member.builder().mId(rs2.getString("M_id")).build()).build();
					do {
						OrderItem orderWithOrderItemList = OrderItem.builder().OiNo(rs2.getInt("oi_NO"))
								.OiQty(rs2.getInt("oi_qty")).order(Order.builder().oNo(rs2.getInt("O_NO")).build())
								.product(Product.builder().pNo(rs2.getInt("P_NO")).build()).build();
						orderWithOrderItem.getOrderItemList().add(orderWithOrderItemList);
					} while (rs2.next());
				}
				orderList.set(i, orderWithOrderItem);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}

	/*
	 * 주문 가격 순 내림차순으로 검색 가능
	 */
	public List<Order> findOrderWithOrderItemByUserIdByTotalPriceDesc(String sUserId) throws Exception {

		List<Order> orderList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = dataSource.getConnection();
			/*
			 * "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = ? order by o.o_totalprice DESC"
			 */
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_ID_BY_TOTALPRICE_DESC);
			pstmt1.setString(1, sUserId);
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				orderList.add(Order.builder().oNo(rs1.getInt("o_no")).oTotalPrice(rs1.getInt("o_totalprice"))
						.oComment(rs1.getString("o_comment")).oAddress(rs1.getString("o_address"))
						.oPhone(rs1.getString("o_phone")).oDate(rs1.getDate("o_date"))
						.member(Member.builder().mId(rs1.getString("M_id")).build()).build());
			}
			pstmt2 = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_BY_O_NO);
			/*
			 * "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.o_no = ?"
			 */
			for (int i = 0; i < orderList.size(); i++) {
				Order tempOrder = orderList.get(i);
				pstmt2.setInt(1, tempOrder.getONo());
				rs2 = pstmt2.executeQuery();
				Order orderWithOrderItem = null;
				if (rs2.next()) {
					orderWithOrderItem = Order.builder().oNo(rs2.getInt("o_No")).oTotalPrice(rs2.getInt("o_TotalPrice"))
							.oComment(rs2.getString("O_Comment")).oAddress(rs2.getString("O_Address"))
							.oPhone(rs2.getString("O_Phone")).oDate(rs2.getDate("O_Date"))
							.member(Member.builder().mId(rs2.getString("M_id")).build()).build();
					do {
						OrderItem orderWithOrderItemList = OrderItem.builder().OiNo(rs2.getInt("oi_NO"))
								.OiQty(rs2.getInt("oi_qty")).order(Order.builder().oNo(rs2.getInt("O_NO")).build())
								.product(Product.builder().pNo(rs2.getInt("P_NO")).build()).build();
						orderWithOrderItem.getOrderItemList().add(orderWithOrderItemList);
					} while (rs2.next());
				}
				orderList.set(i, orderWithOrderItem);
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return orderList;
	}
}
