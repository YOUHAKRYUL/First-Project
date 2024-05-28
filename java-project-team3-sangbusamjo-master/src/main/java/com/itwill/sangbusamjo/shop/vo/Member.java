package com.itwill.sangbusamjo.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*CREATE TABLE Member
m_id                          		VARCHAR2(100)		 NULL
m_pw                          		VARCHAR2(100)		 NULL
m_name                        		VARCHAR2(100)		 NULL
m_phone                       		VARCHAR2(100)		 NULL
m_address                     		VARCHAR2(100)		 NULL
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RequiredArgsConstructor // 인자 1개짜리 생성자
						//(전체 생성자 안써도 쓰고싶은 것만 써서 사용가능)



public class Member {

@NonNull // @RequiredArgsConstructor 을 통해 사용 (필요한 개수 세팅)
	 private String mId; 		// ID
	 private String mPw; 		// PASSWORD
	 private String mName;  	// NAME
	 private String mPhone;		// PHOEN
	 private String mAddress;	// ADDRESS
}
