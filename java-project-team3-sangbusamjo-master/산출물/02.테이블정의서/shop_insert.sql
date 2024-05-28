/*******************member insert****************/
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('JMS123', 'JMS123@', '지민수', '010-2314-5673','경기도 의정부시 가능동');
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('YHR123', 'YHR123@', '유학렬', '010-5123-1263','경기도 성남시 태평동');
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('LJH123', 'LJH123@', '이재훈', '010-1233-5213','서울특별시 송파구 석촌동');
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('CIH123', 'CIH123@', '최인호', '010-4785-9875','경기도 고양시 주엽동');
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('SHM123', 'SHM123@', '심현민', '010-7885-5475','서울시 강남구 역삼동');
INSERT INTO member (m_id, m_pw, m_name, m_phone, m_address) VALUES ('JSB123', 'JSB123@', '정수빈', '010-6585-3475','서울시 강남구 강남동');


/************************product insert*******************/
/******************************상의************************/
insert into Product values(1, '케이블 나라 니트', '기타 상세 정보 등...','상의1',26000,1);
insert into Product values(2, '빅 루즈핏 맨투맨', '기타 상세 정보 등...','상의2',29000,1);
insert into Product values(3, '레이어드 반팔 티셔츠', '기타 상세 정보 등...', '상의3',16000,1);
insert into Product values(4, '미니멀 라운드 니트', '기타 상세 정보 등...','상의4',39000,1);

/******************************하의************************/
insert into Product values(5, '레플리카 퍼티그 팬츠', '기타 상세 정보 등...', '하의1',42000, 2);
insert into Product values(6, '백 사틴 오피서 팬츠', '기타 상세 정보 등...', '하의2',47000, 2);
insert into Product values(7, '이지 와이드 데님 팬츠', '기타 상세 정보 등...', '하의3',21000, 2);
insert into Product values(8, '아디브레이크 팬츠','기타 상세 정보 등...', '하의4',67000, 2);

/******************************신발************************/
insert into Product values(9, '아디매틱 블랙', '기타 상세 정보 등...', '신발1',133000, 3);
insert into Product values(10, '스탠스미스 화이트', '기타 상세 정보 등...', '신발2',98000, 3);
insert into Product values(11, '센트리 올드스쿨 캔버스', '기타 상세 정보 등...', '신발3',59000, 3);
insert into Product values(12, '베이직 캔버스 스니커즈','기타 상세 정보 등...', '신발4',28000, 3);

/******************************아우터************************/
insert into Product values(13, '베이직 푸퍼', '기타 상세 정보 등...', '아우터1',57000, 4);
insert into Product values(14, '캐시미어 블렌드 로브 코트', '기타 상세 정보 등...', '아우터2',180000, 4);
insert into Product values(15, '파이어버드 트랙탑', '기타 상세 정보 등...', '아우터3',114000, 4);
insert into Product values(16, '유니온 플리스 크롭 자켓','기타 상세 정보 등...', '아우터4',22000, 4);


/**********************cart insert************************/
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,1,'YHR123',1);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,1,'YHR123',2);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,1,'JMS123',3);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,2,'JMS123',4);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,2,'JMS123',1);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,2,'JMS123',2);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,2,'JMS123',3);
insert into cart(c_no,c_qty,m_id,p_no) values(cart_c_no_seq.nextval,2,'JMS123',4);


/**********************order, order item insert************************/
insert into orders(o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) 
values(orders_o_no_seq.nextval,1111,'빨리 배달해주세요','경기도 의정부시 가능동','010-2314-5673',sysdate,'JMS123','지민수');  
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,1,orders_o_no_seq.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,2,orders_o_no_seq.currval,2);


insert into orders(o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name)  
values(orders_o_no_seq.nextval,2222,'문 앞에 두세요','경기도 성남시 태평동','010-5123-1263',sysdate,'YHR123','유학렬');  
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,3,orders_o_no_seq.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,4,orders_o_no_seq.currval,2);

insert into orders(o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) 
values(orders_o_no_seq.nextval,3333,'노크해주세요','서울특별시 송파구 석촌동','010-1233-5213',sysdate,'LJH123','이재훈');  
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,1,orders_o_no_seq.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,2,orders_o_no_seq.currval,2);

insert into orders(o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) 
values(orders_o_no_seq.nextval,4444,'벨눌러주세요','경기도 고양시 주엽동','010-4785-9875',sysdate,'CIH123','최인호');  
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,3,orders_o_no_seq.currval,1);
insert into order_item(oi_no,oi_qty,o_no,p_no) values(order_item_oi_no_seq.nextval,4,orders_o_no_seq.currval,2);	

-- 장바구니 Insert
-- yhr1님이 1번 제품 2개 , 3번 제품 1개 5번 제품 5개
INSERT INTO Cart (c_no, c_qty, m_id, p_no) VALUES (Cart_c_no_seq.nextval, 2, 'JMS123', 1);
INSERT INTO Cart (c_no, c_qty, m_id, p_no) VALUES (Cart_c_no_seq.nextval, 1, 'JMS123', 3);
INSERT INTO Cart (c_no, c_qty, m_id, p_no) VALUES (Cart_c_no_seq.nextval, 5, 'JMS123', 5);

-- yhr2님이 4번 제품 1개 5번 제품 2개
INSERT INTO Cart (c_no, c_qty, m_id, p_no) VALUES (Cart_c_no_seq.nextval, 1, 'YHR123', 4);
INSERT INTO Cart (c_no, c_qty, m_id, p_no) VALUES (Cart_c_no_seq.nextval, 2, 'YHR123', 5);


/****************************Cart 안에 있는 아이템들 주문 ***************************************************************/

-- yhr1님 Orders
INSERT INTO Orders (o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) VALUES (Orders_o_no_seq.nextval, 260000, '문 앞에 놓아주세요', '경기도 의정부시 가능동', '010-2314-5673', sysdate, 'JMS123','지민수');
-- OI
INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, 2, Orders_o_no_seq.currval, 1);
INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, 1, Orders_o_no_seq.currval, 3);
INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, 5, Orders_o_no_seq.currval, 5);

-- yhr2님 Orders
INSERT INTO Orders (o_no, o_totalprice, o_comment, o_address, o_phone, o_date, m_id,o_name) VALUES (Orders_o_no_seq.nextval,80000, '노크 해주세요', '경기도 성남시 태평동', '010-5123-1263', sysdate, 'YHR123','유학렬');
-- OI
INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, 1, Orders_o_no_seq.currval, 4);
INSERT INTO Order_Item (oi_no, oi_qty, o_no, p_no) VALUES (Order_Item_oi_no_seq.nextval, 2, Orders_o_no_seq.currval, 5);

