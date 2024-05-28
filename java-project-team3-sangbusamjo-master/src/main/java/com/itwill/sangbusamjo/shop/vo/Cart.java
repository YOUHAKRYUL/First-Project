package com.itwill.sangbusamjo.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Cart {

	/********FK**********/
	private int cNo;
	private int cQty;
	private String img;
	private Member member;
	private Product product;

}
