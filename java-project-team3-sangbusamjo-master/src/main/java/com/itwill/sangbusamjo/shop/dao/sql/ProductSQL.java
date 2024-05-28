package com.itwill.sangbusamjo.shop.dao.sql;

public class ProductSQL {

	//상품 전체 출력
	public static final String PRODUCT_SELECT_ALL=
			"select * from Product";
	//상품 번호로 검색
	public static final String PRODUCT_SELECT_BY_NO=
			"select * from Product where p_no=?";
	//상품 이름으로 검색
	public static final String PRODUCT_SELECT_BY_NAME =
			"select * from product where p_name = ?";
	//상품 분류로 검색
	public static final String PRODUCT_SELECT_BY_CATEGORY =
			"select * from product where p_category = ?";



}
