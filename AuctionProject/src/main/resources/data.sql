insert into category values(1,'ELECTRONICS');
insert into category values(2,'CARS');
insert into category values(3,'FURNITURE');
insert into category values(4,'HOUSE');

insert into address values ( 1,'philadelphia','PA','7520greenhillrd','19151');
insert into address values ( 2,'Dallas','TX','Green Vill','75247');
insert into address values ( 3,'Dallas','TX','8849 Fairoaks Crossing','75243');
insert into address values ( 4,'Dallas','TX','12205 Plano Rd ','75246');
insert into address values ( 5,'Dallas','TX','Green Vill ','75247');

SET foreign_key_checks = 0;


insert into role values(1,'ROLE_ADMIN');
insert into role values (2,'ROLE_SELLER');
insert into role values (3,'ROLE_CUSTOMER');

-- All Users in our DataBase

SET foreign_key_checks = 0;
insert into user values(1,'barefaine@miu.edu','bini','Arefaine','B12345',1,1,4,1,1);
insert into user values(2,'esseytezare@gmail.com','Essey','Abrham','E12345',1,1,1,2,2);
insert into user values(3,'dhailu@miu.edu','Dawit','Hailu','D12345',1,1,5,3,2);
insert into user values(4,'wgebrehawaryat@miu.edu','Wegahta','Gebremariam','W12345',1,1,3,4,3);
insert into user values(5,'eweldeyohannes@miu.edu','Eyob','Weldeyohannes','EY12345',1,1,2,5,3);

---Essey Products as Seller
insert into product values(1,1,400,'Essey Product','2020-12-13',600,0,'2021-12-18','Phone',0,'Yes',0,1,40000.00,'2020-10-12',2);
insert into product values(2,0,400,'Essey Product','2021-03-20',0.00,0,'2021-12-14','Toyota',0,'Yes',0,0,40000.00,'2020-12-12',2);
insert into product values(3,0,400,'Essey Product','2021-03-20',0.00,0,'2021-12-14','Table',0,'Yes',0,0,40000.00,'2020-12-12',2);


--Dawit As a Seller Products

insert into product values(4,0,400,'Dawit Product','2021-03-20',0.00,0,'2021-12-14','Laptop',0,'Yes',0,0,40000.00,'2020-12-12',3);
insert into product values(5,0,400,'Dawit Product','2021-03-20',0.00,0,'2021-12-14','Hyundai',0,'Yes',0,0,40000.00,'2020-12-12',3);
insert into product values(6,0,400,'Dawit Product','2021-03-20',0.00,0,'2021-12-14','Sofa',0,'Yes',0,0,40000.00,'2020-12-12',3);

insert into deposit_payment values (1,'2021-01-02','5678908765378965','565','400',0,'essey',null,1,3);

insert into bid values ( 1,2 );
insert into bid_user values ( 1,600,3 );
insert into USER_WON_PRODUCTS values(3,1);
insert into bid_history values ( 1,600,'2020-12-14',1,3);
-- insert into bid values(1,1);
-- -- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
SET foreign_key_checks = 0;


-- insert into credentials values ( 1,'eyob' ,'eyob');
insert into credentials values ( 1,'$2a$10$QyjwOSYmGYZF.GPrf.7L6.I.aBukmhv9eOVjycF84qDxI.GlIrYXG' ,'bini');
insert into credentials values ( 2,'$2a$10$g791zK/LRxP9ZCy2PyER/u5dePlAUZefNd7yjdFxEE.1oJq.Jd.7q' ,'essey');
insert into credentials values ( 3,'$2a$10$weck1DMNP/zGw5wff5yMAe2V9/3YxXOL/hDxspajAExmJqHTHbh/i' ,'dawit');
insert into credentials values ( 4,'$2a$10$1DZaOowPaGI8lfuVcDWpZ.x0n8564v1EFetULYtDvotAIKoWa8CWa' ,'wegahta');
insert into credentials values ( 5,'$2a$10$wW2qR0wVsCve2bJWzU0SFuBGRhVJeDO9aCP7qHepTsXG29iC1yLMG' ,'eyob');


--- Inserted Photos for products on Essey
insert into product_photos values ( 1,'/images/product-photos/2/phone.jpg');
insert into product_photos values ( 2,'/images/product-photos/2/toyota.jpg');
insert into product_photos values ( 3,'/images/product-photos/2/Table.jpg');


---Inserted Photos for Seller Dawit
insert into product_photos values ( 4,'/images/product-photos/3/laptop.jpg');
insert into product_photos values ( 4,'/images/product-photos/3/laptop2.jpg');
insert into product_photos values ( 5,'/images/product-photos/3/hyudai.jpeg');
insert into product_photos values ( 6,'/images/product-photos/3/sofa.jpg');


------------------ Poducts Catagory----------
insert into product_categories values(3,3);
insert into product_categories values(2,2);
insert into product_categories values(1,1);
insert into product_categories values(4,1);
insert into product_categories values(5,2);
insert into product_categories values(6,3);