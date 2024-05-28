package com.itwill.sangbusamjo.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderItem {

	/*
OI_NO	NUMBER(10,0)	No
OI_QTY	NUMBER(10,0)	Yes
O_NO	NUMBER(10,0)	Yes
P_NO	NUMBER(10,0)	Yes
	 */

	private int OiNo;
	private int OiQty;
	private Order order;
	private Product product;

}
