package com.itwill.sangbusamjo.shop.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder


public class Order {

/*
O_NO	        NUMBER(10,0)	        No
O_TOTALPRICE	NUMBER(10,0)	        Yes
O_COMMENT	    VARCHAR2(100 BYTE)	    Yes
O_ADDRESS	    VARCHAR2(100 BYTE)	    Yes
O_PHONE     	VARCHAR2(100 BYTE)	    Yes
O_DATE	        DATE	                Yes
M_ID	        VARCHAR2(100 BYTE)	    Yes
<<<<<<< HEAD
 */


	/*
	 * O_NO NUMBER(10,0) No O_TOTALPRICE NUMBER(10,0) Yes O_COMMENT VARCHAR2(100
	 * BYTE) Yes O_ADDRESS VARCHAR2(100 BYTE) Yes O_PHONE VARCHAR2(100 BYTE) Yes
	 * O_DATE DATE Yes M_ID VARCHAR2(100 BYTE) Yes
<<<<<<< HEAD
	 */


	private int oNo;
	private int oTotalPrice;
	private String oComment;
	private String oAddress;
	private String oPhone;
	private Date oDate;
	private String oName;
	private Member member;

	/********** List (OrderItem) **************/
	private List<OrderItem> orderItemList;

	public Order() {
		orderItemList = new ArrayList<>();

	}
	public Order(int oNo, int oTotalPrice, String oComment, String oAddress, String oPhone, Date oDate,String oName,
			List<OrderItem> orderItemList) {
		this.oNo = oNo;
		this.oTotalPrice = oTotalPrice;
		this.oComment = oComment;
		this.oAddress = oAddress;
		this.oPhone = oPhone;
		this.oDate = oDate;
		this.oName = oName;
		this.member = new Member();

		if (orderItemList != null) {
			this.orderItemList = orderItemList;
		} else {
			this.orderItemList = new ArrayList<>();
		}
	}
	public Order(int oNo, int oTotalPrice, String oComment, String oAddress, String oPhone, Date oDate,String oName,Member member,
			List<OrderItem> orderItemList) {
		this.oNo = oNo;
		this.oTotalPrice = oTotalPrice;
		this.oComment = oComment;
		this.oAddress = oAddress;
		this.oPhone = oPhone;
		this.oDate = oDate;
		this.member = member;
		this.oName = oName;

		if (orderItemList != null) {
			this.orderItemList = orderItemList;
		} else {
			this.orderItemList = new ArrayList<>();
		}

	}

}
