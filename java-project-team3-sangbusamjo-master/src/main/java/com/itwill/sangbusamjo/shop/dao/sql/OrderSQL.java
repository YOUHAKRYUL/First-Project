package com.itwill.sangbusamjo.shop.dao.sql;

public class OrderSQL {

	public final static String ORDER_DELETE_BY_NO = "delete from orders where o_no = ?";
	public final static String ORDER_DELETE_BY_ID = "delete from orders where m_id = ?";
	public final static String ORDER_SELECT_BY_ID = "select * from orders where m_id = ?";
	public final static String ORDER_SELECT_WITH_ORDERITEM_BY_ID = "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = ?";
	public final static String ORDER_SELECT_WITH_ORDERITEM_BY_O_NO = "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.o_no = ?";
	public final static String ORDER_SELECT_WITH_ORDERITEM_BY_ID_BY_DATE_DESC = "select * from orders where m_id=? order by o_date desc";
	public final static String ORDER_SELECT_WITH_ORDERITEM_BY_ID_BY_TOTALPRICE_DESC = "select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = ? order by o.o_totalprice DESC";
	public final static String ORDER_INSERT = "INSERT INTO Orders (o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) VALUES (Orders_o_no_seq.nextval, ?, ?, ?, ?, sysdate, ?,?)";
	public final static String ORDERITEM_INSERT = "INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, ?, Orders_o_no_seq.currval, ?)";

}
