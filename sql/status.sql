
insert into order_sequence values(1);
insert into global_sequence values(1);
insert into cust_ord_sequence values(1);
insert into customer_sequence values(1);
insert into address_sequence values(1);


-- Product Status Type
INSERT INTO product_status_type(PRODUCT_STATUS_TYPE_ID,PRODUCT_STATUS_NAME,PRODUCT_STATUS_CODE,CREATE_DATE_TIME) VALUES(100,'In Stock','IS',utc_timestamp());
INSERT INTO product_status_type(PRODUCT_STATUS_TYPE_ID,PRODUCT_STATUS_NAME,PRODUCT_STATUS_CODE,CREATE_DATE_TIME) VALUES(101,'Sold Out','SO',utc_timestamp());
INSERT INTO product_status_type(PRODUCT_STATUS_TYPE_ID,PRODUCT_STATUS_NAME,PRODUCT_STATUS_CODE,CREATE_DATE_TIME) VALUES(102,'Discontinued','DC',utc_timestamp());
INSERT INTO product_status_type(PRODUCT_STATUS_TYPE_ID,PRODUCT_STATUS_NAME,PRODUCT_STATUS_CODE,CREATE_DATE_TIME) VALUES(103,'Out of Stock','OOS',utc_timestamp());

-- General Status Type
INSERT INTO gen_status_type(GEN_STATUS_TYPE_ID,STATUS_NAME,STATUS_CODE,CREATE_DATE_TIME) VALUES(200, 'Active', 'ACT', utc_timestamp());
INSERT INTO gen_status_type(GEN_STATUS_TYPE_ID,STATUS_NAME,STATUS_CODE,CREATE_DATE_TIME) VALUES(201, 'In Active', 'IAT', utc_timestamp());

-- Admin User
INSERT INTO admin_user(ADMIN_USER_ID,USER_NAME,PASSWORD,USER_EMAIL,CREATE_DATE_TIME)
VALUES(100,'admin',password('prannu14'),'deep.devalla@gmail.com',utc_timestamp());

-- Address Type
INSERT INTO ADDRESS_TYPE(ADDRESS_TYPE_ID, ADDRESS_TYPE_NAME, ADDRESS_TYPE_CODE, CREATE_DATE_TIME) 
VALUES(100, 'Shipping', 'S', utc_timestamp());
INSERT INTO ADDRESS_TYPE(ADDRESS_TYPE_ID, ADDRESS_TYPE_NAME, ADDRESS_TYPE_CODE, CREATE_DATE_TIME) 
VALUES(101, 'Billing', 'B', utc_timestamp());

-- State
INSERT INTO STATE(STATE_ID, STATE_NAME, STATE_CODE, CREATE_DATE_TIME) VALUES (100, 'Andhra Pradesh', 'AP', UTC_TIMESTAMP());

-- Payment Type
INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, PAYMENT_CARD, PAYMENT_CODE, CREATE_DATE_TIME)
VALUES (1000, 'Cash on Delivery', 'COD', utc_timestamp());
INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, PAYMENT_CARD, PAYMENT_CODE, CREATE_DATE_TIME)
VALUES (1001, 'Credit/Debit', 'CRD', utc_timestamp());
INSERT INTO PAYMENT_TYPE (PAYMENT_TYPE_ID, PAYMENT_CARD, PAYMENT_CODE, CREATE_DATE_TIME)
VALUES (1002, 'Net Banking', 'NB', utc_timestamp());

-- Department Type
INSERT INTO department_type(DEPARTMENT_TYPE_ID,DEPT_TYPE_NAME,DEPT_TYPE_DESC,CREATE_DATE_TIME)
VALUES(100,'Grocery','Groceries', utc_timestamp());

INSERT INTO department_type(DEPARTMENT_TYPE_ID,DEPT_TYPE_NAME,DEPT_TYPE_DESC,CREATE_DATE_TIME)
VALUES(101,'Baby & Kids','Baby & Kids', utc_timestamp());

INSERT INTO department_type(DEPARTMENT_TYPE_ID,DEPT_TYPE_NAME,DEPT_TYPE_DESC,CREATE_DATE_TIME)
VALUES(102,'Bath & Body','Bath & Body', utc_timestamp());


-- Category
INSERT INTO category(CATEGORY_ID,CATEGORY_NAME,DEPARTMENT_TYPE_ID,CATEGORY_STATUS_ID,CREATE_DATE_TIME)
VALUES(100,'Rice',100,200,utc_timestamp());

INSERT INTO category(CATEGORY_ID,CATEGORY_NAME,DEPARTMENT_TYPE_ID,CATEGORY_STATUS_ID,CREATE_DATE_TIME)
VALUES(101,'Lentils (Dals) & Beans',100,200,utc_timestamp());

INSERT INTO category(CATEGORY_ID,CATEGORY_NAME,DEPARTMENT_TYPE_ID,CATEGORY_STATUS_ID,CREATE_DATE_TIME)
VALUES(102,'Beverages',100,200,utc_timestamp());

INSERT INTO category(CATEGORY_ID,CATEGORY_NAME,DEPARTMENT_TYPE_ID,CATEGORY_STATUS_ID,CREATE_DATE_TIME)
VALUES(103,'Nuts & Dry Fruits',100,200,utc_timestamp());

INSERT INTO category(CATEGORY_ID,CATEGORY_NAME,DEPARTMENT_TYPE_ID,CATEGORY_STATUS_ID,CREATE_DATE_TIME)
VALUES(104,'Spices & Masala',100,200,utc_timestamp());

-- Sub Category Name
INSERT INTO sub_category_name(SUB_CATEGORY_NAME_ID,SUB_CATEGORY_NAME,SUB_CATEGORY_CODE,SPRING_LABEL,CREATE_DATE_TIME)
VALUES(100,'Sona Massori','SM','label.sonaMassori',utc_timestamp());

INSERT INTO sub_category_name(SUB_CATEGORY_NAME_ID,SUB_CATEGORY_NAME,SUB_CATEGORY_CODE,SPRING_LABEL,CREATE_DATE_TIME)
VALUES(101,'Basmati','BS','label.basmati',utc_timestamp());

INSERT INTO sub_category_name(SUB_CATEGORY_NAME_ID,SUB_CATEGORY_NAME,SUB_CATEGORY_CODE,SPRING_LABEL,CREATE_DATE_TIME)
VALUES(102,'Toor Dal','TOD','label.toorDal',utc_timestamp());

INSERT INTO sub_category_name(SUB_CATEGORY_NAME_ID,SUB_CATEGORY_NAME,SUB_CATEGORY_CODE,SPRING_LABEL,CREATE_DATE_TIME)
VALUES(103,'Moong Dal','MOD','label.moongDal',utc_timestamp());

-- Sub Category
INSERT INTO sub_category(SUB_CATEGORY_ID,SUB_CATEGORY_NAME_ID,SUB_CAT_STATUS_TYPE_ID,CATEGORY_ID,CREATE_DATE_TIME)
VALUES(100,100,200,100,utc_timestamp());

INSERT INTO sub_category(SUB_CATEGORY_ID,SUB_CATEGORY_NAME_ID,SUB_CAT_STATUS_TYPE_ID,CATEGORY_ID,CREATE_DATE_TIME)
VALUES(101,101,200,100,utc_timestamp());

INSERT INTO sub_category(SUB_CATEGORY_ID,SUB_CATEGORY_NAME_ID,SUB_CAT_STATUS_TYPE_ID,CATEGORY_ID,CREATE_DATE_TIME)
VALUES(102,102,200,101,utc_timestamp());

INSERT INTO sub_category(SUB_CATEGORY_ID,SUB_CATEGORY_NAME_ID,SUB_CAT_STATUS_TYPE_ID,CATEGORY_ID,CREATE_DATE_TIME)
VALUES(103,103,200,101,utc_timestamp());


-- Order Status Type

INSERT INTO order_status_type(order_status_type_id, order_status_name, order_status_code, create_date_time) 
values (100, 'Order Received', 'OR', utc_timestamp());

INSERT INTO order_status_type(order_status_type_id, order_status_name, order_status_code, create_date_time) 
values (101, 'In Process', 'IP', utc_timestamp());

INSERT INTO order_status_type(order_status_type_id, order_status_name, order_status_code, create_date_time) 
values (102, 'Out for Delivery', 'OD', utc_timestamp());

INSERT INTO order_status_type(order_status_type_id, order_status_name, order_status_code, create_date_time) 
values (103, 'Cancelled', 'C', utc_timestamp());

INSERT INTO order_status_type(order_status_type_id, order_status_name, order_status_code, create_date_time) 
values (104, 'Delivered', 'D', utc_timestamp());

-- Payment Status Type

INSERT INTO PAYMENT_STATUS(PAYMENT_STATUS_ID, PAYMENT_STATUS_NAME, PAYMENT_STATUS_CODE, CREATE_DATE_TIME)
VALUES (100, 'Payment Initiated', 'PI', utc_timestamp());

INSERT INTO PAYMENT_STATUS(PAYMENT_STATUS_ID, PAYMENT_STATUS_NAME, PAYMENT_STATUS_CODE, CREATE_DATE_TIME)
VALUES (101, 'Payment Received', 'PR', utc_timestamp());

INSERT INTO PAYMENT_STATUS(PAYMENT_STATUS_ID, PAYMENT_STATUS_NAME, PAYMENT_STATUS_CODE, CREATE_DATE_TIME)
VALUES (102, 'Payment Error', 'PE', utc_timestamp());

INSERT INTO PAYMENT_STATUS(PAYMENT_STATUS_ID, PAYMENT_STATUS_NAME, PAYMENT_STATUS_CODE, CREATE_DATE_TIME)
VALUES (103, 'Cash on Delivery', 'COD', utc_timestamp());

-- Product
INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(100,'Sona Massori 5kg Nirav','Sona Massori 5kg Nirav',500.00,100,'/images/sona_massori_small.png','/images/sona_massori_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(101,'Sona Massori 10kg Nirav','Sona Massori 10kg Nirav',1000.00,100,'/images/sona_massori_small.png','/images/sona_massori_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(102,'Sona Massori 5kg Deep','Sona Massori 5kg Deep',550.00,100,'/images/sona_massori_small.png','/images/sona_massori_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(103,'Sona Massori 10kg Deep','Sona Massori 10kg Deep',1100.00,100,'/images/sona_massori_small.png','/images/sona_massori_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(104,'Basmati 5kg Tilda','Basmati 5kg Tilda',600.00,100,'/images/basmati_small.png','/images/basmati_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(105,'Basmati 10kg Tilda','Basmati 10kg Tilda',1200.00,100,'/images/basmati_small.png','/images/basmati_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(106,'Basmati 5kg Zaafrani','Basmati 5kg Zaafrani',620.00,100,'/images/basmati_small.png','/images/basmati_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(107,'Basmati 10kg Zaafrani','Basmati 10kg Zaafrani',1240.00,100,'/images/basmati_small.png','/images/basmati_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(108,'Toor Dal 5kg Deep','Toor Dal 5kg Deep',800.00,100,'/images/toordal_small.png','/images/toordal_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(109,'Toor Dal 10kg Deep','Toor Dal 10kg Deep',1600.00,100,'/images/toordal_large.png','/images/toordal_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(110,'Toor Dal 5kg Nirav','Toor Dal 5kg Nirav',800.00,100,'/images/toordal_small.png','/images/toordal_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(111,'Toor Dal 10kg Nirav','Toor Dal 10kg Nirav',1600.00,100,'/images/toordal_large.png','/images/toordal_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(112,'Moong Dal 5kg Lakshmi','Moong Dal 5kg Lakshmi',850.00,100,'/images/moongdal_small.png','/images/moongdal_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(113,'Moong Dal 10kg Lakshmi','Moong Dal 10kg Lakshmi',1700.00,100,'/images/moongdal_large.png','/images/moongdal_large.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(114,'Moong Dal 5kg Nirav','Moong Dal 5kg Nirav',800.00,100,'/images/moongdal_small.png','/images/moongdal_small.png','admin',utc_timestamp());

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESC,PRODUCT_COST,PRODUCT_STATUS_TYPE_ID,SMALL_IMG_URL,LARGE_IMG_URL,UPDATED_BY,CREATE_DATE_TIME)
VALUES(115,'Moong Dal 10kg Nirav','Moong Dal 10kg Nirav',1600.00,100,'/images/moongdal_large.png','/images/moongdal_large.png','admin',utc_timestamp());

-- Brand Name
INSERT INTO brand_name(BRAND_NAME_ID, BRAND_NAME, BRAND_CODE, SPRING_LABEL, CREATE_DATE_TIME)
VALUES(100, 'Deep', 'DP', null, utc_timestamp());

INSERT INTO brand_name(BRAND_NAME_ID, BRAND_NAME, BRAND_CODE, SPRING_LABEL, CREATE_DATE_TIME)
VALUES(101, 'Lakshmi', 'LK', null, utc_timestamp());

INSERT INTO brand_name(BRAND_NAME_ID, BRAND_NAME, BRAND_CODE, SPRING_LABEL, CREATE_DATE_TIME)
VALUES(102, 'Swad', '', null, utc_timestamp());

-- Brand
INSERT INTO brand(BRAND_ID, BRAND_NAME_ID, SUB_CATEGORY_ID, BRAND_STATUS_ID, CREATE_DATE_TIME)
VALUES(100, 100, 100, 200, utc_timestamp());

INSERT INTO brand(BRAND_ID, BRAND_NAME_ID, SUB_CATEGORY_ID, BRAND_STATUS_ID, CREATE_DATE_TIME)
VALUES(101, 101, 100, 200, utc_timestamp());

-- Product Department
INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(100,100,100,100,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(101,101,100,100,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(102,108,101,102,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(103,112,101,103,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(104,102,100,100,100,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(105,103,100,100,100,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(106,104,100,100,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(107,105,100,100,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(108,106,100,100,null,'admin',utc_timestamp());

INSERT INTO product_dept(PRODUCT_DEPT_ID,PRODUCT_ID,CATEGORY_ID,SUB_CATEGORY_ID,BRAND_ID,UPDATED_BY,CREAT_DATE_TIME)
VALUES(109,107,100,100,null,'admin',utc_timestamp());



-- Product Quantity
INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(100, 100, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(101, 101, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(102, 102, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(103, 103, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(104, 104, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(105, 105, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(106, 106, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(107, 107, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(108, 108, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(109, 109, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(110, 110, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(111, 111, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(112, 112, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(113, 113, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(114, 114, 100, utc_timestamp());

INSERT INTO product_quantity(PRODUCT_QUANTITY_ID, PRODUCT_ID, QUANTITY, CREATE_DATE_TIME) 
VALUES(115, 115, 100, utc_timestamp());




