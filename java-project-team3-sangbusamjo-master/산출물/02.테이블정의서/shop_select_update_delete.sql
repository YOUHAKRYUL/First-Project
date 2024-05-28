/***************회원******************/
--회원 전체보기
select * from member;

--회원가입
insert into member(m_id,m_pw,m_name,m_phone,m_address) values('test5','5555','테스트5','010-5555-5555,','서울시 테스트구 5동'); 

--로그인한회원의 정보보기(select pk)
select * from member where m_id = 'test1';

--로그인한회원의 회원의정보수정(update pk)
update member set m_pw = '1234', m_name = '테스트1변경', m_phone='010-1234-1243',m_address='서울시 강남구 테스트동' where m_id = 'test1';

--로그인한 회원의 회원탈퇴(delete pk)
delete member where m_id='test4';

--아이디찾기
select member.m_id from member where m_name = '테스트2' and m_phone = '010-2222-2222';

--비밀번호찾기
select member.m_pw from member where m_id = 'test2' and m_name = '테스트2';

/****************제품*****************/
--제품전체리스트
select * from product;
--제품상세보기
select * from product where p_no=1;

--제품검색
select * from product where p_name = '케이블 나라 니트';

--제품분류
select p_image,p_category from product where p_category = 1;

--제품카테고리
select * from product where p_category = 1;

--제품수정
update product set p_name = '변경제품' , p_desc = '변경입니다' , p_image = 'O' , p_price = 0, p_category = '2' where p_no = '6';
--제품삭제
delete product where p_no = '7';

/***********************카트************************/

--로그인한멤버(yhr1)의 카트리스트
select * 
from cart 
where m_id = 'yhr1';

select c.* , p.* , c.c_qty * p.p_price as "총 가격" 
from cart c 
join product p 
on c.p_no = p.p_no
where m_id = 'test1';

--로그인한멤버(yhr2)의 카트리스트삭제
delete from cart where m_id = 'yhr2';

--로그인한멤버(yhr1)의 카트아이템 1개 삭제
delete from cart where c_no = 2 and m_id = 'yhr1';

--로그인한멤버(yhr1)의 카트1개아이템 수량증가(+)
update cart set c_qty = c_qty+1 where m_id = 'yhr1' and p_no = 1;

--로그인한멤버(yhr1)의 카트1개아이템 수량감소(-)
update cart set c_qty = c_qty-1 where m_id = 'yhr1' and p_no = 1;

--로그인한멤버(yhr1)의 카트에있는 카트1개아이템의 수량 수정
update cart set c_qty=3 where c_no=1 and m_id= 'yhr1';

--로그인한멤버(yhr1)의 카트에 존재하는 제품의수(제품존재여부판단) -> 1 = 제품 [O] / 0 = 제품 [X]
select count(*) as p_count from cart where m_id = 'test1' and p_no =1;


insert into cart(c_no,c_qty,p_no,m_id) values (cart_c_no_SEQ.nextval,1,3,'test1');

--로그인한멤버(yhr1)의 카트에 담기(존재하는 제품수정)
--update cart set c_qty=c_qty+3 where m_id = 'yhr1' and p_no = 1;

/****************주문********************/
--1.멤버한사람의 주문전체목록(yhr1)
select * from orders where m_id = 'yhr1';

--1.멤버한사람의 주문(주문아이템+제품)전체목록(yhr1)
select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.m_id = 'yhr1';

--2.멤버한사람의 주문(주문아이템+제품)한개(yhr1)
select * from orders o join order_item oi on o.o_no = oi.o_no join product p on oi.p_no = p.p_no where o.o_no = 1 and o.m_id = 'yhr1';

--3.로그인한멤버(yhr1)주문한개삭제
-- on delete cascade 설정
delete from orders where o_no = 2;

--4. 로그인한멤버(yhr1)주문전체삭제
delete from orders where m_id = 'yhr1';