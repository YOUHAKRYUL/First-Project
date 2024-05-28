DROP TABLE Order_Item CASCADE CONSTRAINTS;
DROP TABLE Cart CASCADE CONSTRAINTS;
DROP TABLE Orders CASCADE CONSTRAINTS;
DROP TABLE Product CASCADE CONSTRAINTS;
DROP TABLE Member CASCADE CONSTRAINTS;

CREATE TABLE Member(
		m_id                          		VARCHAR2(100)		 NULL ,
		m_pw                          		VARCHAR2(100)		 NULL ,
		m_name                        		VARCHAR2(100)		 NULL ,
		m_phone                       		VARCHAR2(100)		 NULL ,
		m_address                     		VARCHAR2(100)		 NULL 
);


CREATE TABLE Product(
		p_no                          		NUMBER(10)		 NULL ,
		p_name                        		VARCHAR2(100)		 NULL ,
		p_desc                        		VARCHAR2(100)		 NULL ,
		p_image                       		VARCHAR2(100)		 NULL ,
		p_price                       		NUMBER(10)		 NULL ,
		p_category                    		NUMBER(10)		 NULL 
);

DROP SEQUENCE Product_p_no_SEQ;

CREATE SEQUENCE Product_p_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;



CREATE TABLE Orders(
		o_no                          		NUMBER(10)		 NULL ,
		o_totalprice                  		NUMBER(10)		 NULL ,
		o_comment                     		VARCHAR2(100)		 NULL ,
		o_address                     		VARCHAR2(100)		 NULL ,
		o_phone                       		VARCHAR2(100)		 NULL ,
		o_date                        		DATE		 DEFAULT sysdate		 NULL ,
        o_name                              VARCHAR2(100)       NULL,
		m_id                          		VARCHAR2(100)		 NULL 
);

DROP SEQUENCE Orders_o_no_SEQ;

CREATE SEQUENCE Orders_o_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;



CREATE TABLE Cart(
		c_no                          		NUMBER(10)		 NULL ,
		c_qty                         		NUMBER(10)		 NULL ,
		m_id                          		VARCHAR2(50)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

DROP SEQUENCE Cart_c_no_SEQ;

CREATE SEQUENCE Cart_c_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;



CREATE TABLE Order_Item(
		oi_no                         		NUMBER(10)		 NULL ,
		oi_qty                        		NUMBER(10)		 NULL ,
		o_no                          		NUMBER(10)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

DROP SEQUENCE Order_Item_oi_no_SEQ;

CREATE SEQUENCE Order_Item_oi_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;


ALTER TABLE Member ADD CONSTRAINT IDX_Member_PK PRIMARY KEY (m_id);

ALTER TABLE Product ADD CONSTRAINT IDX_Product_PK PRIMARY KEY (p_no);

ALTER TABLE Orders ADD CONSTRAINT IDX_Orders_PK PRIMARY KEY (o_no);
ALTER TABLE Orders ADD CONSTRAINT IDX_Orders_FK0 FOREIGN KEY (m_id) REFERENCES Member (m_id) on delete cascade;

ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_PK PRIMARY KEY (c_no);
ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_FK0 FOREIGN KEY (m_id) REFERENCES Member (m_id) on delete cascade;
ALTER TABLE Cart ADD CONSTRAINT IDX_Cart_FK1 FOREIGN KEY (p_no) REFERENCES Product (p_no);

ALTER TABLE Order_Item ADD CONSTRAINT IDX_Order_Item_PK PRIMARY KEY (oi_no);
ALTER TABLE Order_Item ADD CONSTRAINT IDX_Order_Item_FK0 FOREIGN KEY (o_no) REFERENCES Orders (o_no) on delete cascade;
ALTER TABLE Order_Item ADD CONSTRAINT IDX_Order_Item_FK1 FOREIGN KEY (p_no) REFERENCES Product (p_no);

