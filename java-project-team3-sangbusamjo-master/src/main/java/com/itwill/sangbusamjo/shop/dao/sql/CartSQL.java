package com.itwill.sangbusamjo.shop.dao.sql;

public class CartSQL {

	// 카트리스트 추가
	public static final String CART_INSERT = "insert into cart(c_no,c_qty,p_no,m_id) values (cart_c_no_SEQ.nextval,?,?,?)";

    // 로그인한 멤버 카트리스트
	public static final String CART_LIST = "select c.*,p.*,c.c_qty*p.p_price as 총가격 from cart c join product p on c.p_no = p.p_no where m_id = ?";

	// 로그인한멤버의 카트리스트 삭제
	public static final String CART_LIST_DELETE = "delete from cart where m_id= ? and c_no = ?";

	// 로그인한멤버의 카트리스트 전체삭제
	public static final String CART_LIST_DELETE_ALL = "delete from cart where m_id = ?";

	// 로그인한멤버의 카트1개아이템 수량 증가
	public static final String CART_QTY_UPDATE_U = "update cart set c_qty = c_qty+1 where m_id = ? and p_no = ?";

	// 로그인한멤버의 카트1개아이템 수량 감소
	public static final String CART_QTY_UPDATE_D = "update cart set c_qty = c_qty-1 where m_id = ? and p_no = ?";

	// 로그인한멤버의 카트에담은 상품 수량 업데이트
	public static final String CART_PRODUCT_UPDATE = "update cart set c_qty= c_qty + ? where m_id= ? and p_no = ?";

	// 로그인한멤버(yhr1)의 카트에 존재하는 제품의수(제품존재여부판단) -> 1 = 제품 [O] / 0 = 제품 [X]
	public static final String CART_PRODUCT_COUNT_EXIST ="select count(*) as p_count from cart where m_id = ? and p_no = ?";

	// 로그인한 멤버의 카트번호로 카트 아이템 찾기
	public static final String CART_SELECT_BY_CART_NO = "select * from cart c join product p on c.p_no=p.p_no where c_no = ?";
	
	// 로그인한 멤버의 카트번호로 카트아이템 삭제
		public static final String CART_LIST_DELETE_NO = "delete from cart where c_no = ?";

}