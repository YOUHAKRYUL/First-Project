package com.itwill.sangbusamjo.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private int pNo;        //상품번호
	private String pName;   //상품이름
	private String pDesc;   //상품설명
	private String pImage;  //상품이미지
	private int pPrice;     //상품가격
	private int pCategory;  //상품분류


}
