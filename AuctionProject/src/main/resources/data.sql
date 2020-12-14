-- SET foreign_key_checks = 0;
--
-- -- insert into bid values(2,'2020-05-02',2);
-- -- insert into bid values(3,'2019-05-02',3);
-- -- --
-- -- SET foreign_key_checks=0;
-- -- insert into role values(1,'ROLE_ADMIN');
-- -- insert into role values (2,'ROLE_SELLER');
-- -- insert into role values (3,'ROLE_CUSTOMER');
-- --
-- -- SET foreign_key_checks = 0;
-- -- insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- --
-- -- SET foreign_key_checks = 0;
-- -- insert into credentials values ( 1,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'essey');
-- -- -- -- insert into credentials values ( 1,'eyob' ,'eyob');
-- -- -- -- insert into credentials values ( 2,'biniam' ,'biniam');
-- -- -- -- insert into credentials values ( 3,'dawit' ,'dawit');
-- -- -- -- insert into credentials values ( 4,'essey' ,'essey');
-- -- -- -- insert into credentials values ( 5,'wegahta' ,'wegahta');
-- -- SET foreign_key_checks = 0;
-- -- insert into address values ( 1,'7520greenhillrd','philadelphia','PA','19151');
-- -- insert into address values ( 2,'2000 N court','fairfield','IA','52556');
-- -- insert into address values ( 3,'01301 Abrahams road','dallas','TX','75243');
-- -- insert into address values ( 4,'1230 mainstreet','washignton','DC','20039');
-- -- insert into address values ( 5,'2222 forestlane','maryland','ML','39403');
-- -- --
-- -- insert into user values(1,'eyob@gmail.com','Eyob','Habtom','ey0123',0,1,1,2);
-- -- insert into user values(2,'bini@gmail.com','Biniam','Tshaye','bi0234',0,2,1,2);
-- -- insert into user values(3,'dawit@gmail.com','Dawit','tesfahanes','da0567',0,3,1,2);
-- -- insert into user values(4,'essey@gmail.com','Essey','Abreham','es0789',0,1,1,2);
-- -- insert into user values(5,'wegahta@gmail.com','Wegahta','Gerbremariam','we0213',0,3,1,2);
-- --
-- -- --
-- -- -- SET foreign_key_checks = 0;
-- insert into category values(1,'ELECTRONICS');
-- insert into category values(2,'CARS');
-- insert into category values(3,'FURNITURE');
-- -- --
-- -- -- SET foreign_key_checks = 0;
-- -- insert into product_categories values(1,1);
-- -- insert into product_categories values(2,2);
-- -- insert into product_categories values(3,3);
-- -- --
-- SET foreign_key_checks = 0;
-- insert into bid_user values(1,300,1);
-- insert into bid_user values(2,10000,2);
-- -- -- --
-- -- -- --
-- -- -- SET foreign_key_checks = 0;
-- -- insert into deposit_payment values(1,90,2,4);
-- -- insert into deposit_payment values(2,200,3,5);
-- -- -- --
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into product_image_link values(1,'main/resource/images');
-- -- -- -- insert into product_image_link values(2,'main/resource/images');
-- -- --
-- -- --
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into bid values(1,'2020-03-02',null);
-- -- -- -- insert into bid values(2,'2020-05-02',null);
-- -- -- -- -- insert into bid values(3,'2019-05-02',3);
-- -- -- --
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into role values(1,'ROLE_ADMIN');
-- -- -- -- insert into role values (2,'ROLE_SELLER');
-- -- -- -- insert into role values (3,'ROLE_CUSTOMER');
-- -- -- --
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into user values(1,'eyob@gmail.com','bini','Habtom','ey0123',0,null,1,2);
-- -- -- --
-- -- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- -- insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',null);
-- -- -- -- -- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- -- -- -- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- -- -- --
-- -- -- -- -- insert into credentials values ( 1,'eyob' ,'eyob');
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into credentials values ( 1,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'essey');
-- -- -- -- -- insert into credentials values ( 2,'dawit' ,'dawit');
--
-- -- -- -- -- insert into credentials values ( 4,'essey' ,'essey');
-- -- -- -- -- insert into credentials values ( 5,'wegahta' ,'wegahta');
-- -- -- -- -- -- SET foreign_key_checks = 0;
-- insert into address values ( 1,'philadelphia','PA','7520greenhillrd','19151');
-- insert into address values ( 2,'fairfield','IA','2000 N court','52556');
-- -- -- -- -- insert into address values ( 3,'01301 Abrahams road','dallas','TX','75243');
-- -- -- -- -- insert into address values ( 4,'1230 mainstreet','washignton','DC','20039');
-- -- -- -- -- insert into address values ( 5,'2222 forestlane','maryland','ML','39403');
-- -- --
-- SET foreign_key_checks = 0;
-- insert into bid values(1,1);
-- insert into bid values(2,2);
-- insert into bid values(3,3);
--
-- insert into role values(1,'ROLE_ADMIN');
-- insert into role values (2,'ROLE_SELLER');
-- insert into role values (3,'ROLE_CUSTOMER');
-- SET foreign_key_checks = 0;
-- insert into user values(1,'eyob@gmail.com','bini','Habtom','ey0123',1,1,null,1,1);
-- insert into user values(2,'eyob@gmail.com','essey','Habtom','ey0123',1,1,1,2,2);
-- insert into user values(3,'eyob@gmail.com','wegahta','Habtom','ey0123',1,1,2,3,3);
--
-- SET foreign_key_checks = 0;
-- -- insert into credentials values ( 1,'eyob' ,'eyob');
-- insert into credentials values ( 1,'$2a$10$xRx8xiDeumkhJz5SYeIzxujH3Z78fjLjSbMTGhN1j0xstTF3zJojC' ,'bini');
-- insert into credentials values ( 2,'$2a$10$5YTtklhmcF5D55rUe0Jw3uOguHyttmeGwFHCa4PWMxznYIyRczJr.' ,'seller');
-- insert into credentials values ( 3,'$2a$10$7HLAc9IuKNUC6T.TK4teou8IG9BxVDdYxgVa3QGPtBPeiaxGZspSO' ,'dawit');
-- SET foreign_key_checks = 0;
-- insert into product values(1,1,400.0,'perfect product','2020-12-10',45000.0,0,null,'car',0,'Yes',0,0,40000.00,'2020-03-03',2);
-- insert into product values(2,0,500.0,'perfect product','2021-12-10',20000.00,0,null,'car',0,'Yes',0,0,20000.00,'2020-03-03',2);
-- -- insert into bid values(1,1);
-- -- insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
--
-- insert into product_photos values ( 1,'/images/product-photos/2/322868_1100-1100x628.jpg');
-- insert into product_photos values ( 2,'/images/product-photos/2/download.jpeg');
-- -- insert into USER_WON_PRODUCTS values ( 3,1 );
-- --
-- insert into deposit_payment values (1,'2021-01-02','32323232','323',34.00,0,'essey','2020-10-12',1,1);
-- insert into deposit_payment values (2,'2021-01-02','32323232','323',34.00,0,'essey','2020-10-12',1,3);
--
-- insert into BID_USER values ( 1,45000.00,3 );

-- SET foreign_key_checks = 0;

-- insert into bid values(2,'2020-05-02',2);
-- insert into bid values(3,'2019-05-02',3);
-- --
-- SET foreign_key_checks=0;
-- insert into role values(1,'ROLE_ADMIN');
-- insert into role values (2,'ROLE_SELLER');
-- insert into role values (3,'ROLE_CUSTOMER');
--
-- SET foreign_key_checks = 0;
-- insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- --
-- SET foreign_key_checks = 0;
-- insert into credentials values ( 1,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'essey');
-- -- -- insert into credentials values ( 1,'eyob' ,'eyob');
-- -- -- insert into credentials values ( 2,'biniam' ,'biniam');
-- -- -- insert into credentials values ( 3,'dawit' ,'dawit');
-- -- -- insert into credentials values ( 4,'essey' ,'essey');
-- -- -- insert into credentials values ( 5,'wegahta' ,'wegahta');
-- SET foreign_key_checks = 0;
-- insert into address values ( 1,'7520greenhillrd','philadelphia','PA','19151');
-- insert into address values ( 2,'2000 N court','fairfield','IA','52556');
-- insert into address values ( 3,'01301 Abrahams road','dallas','TX','75243');
-- insert into address values ( 4,'1230 mainstreet','washignton','DC','20039');
-- insert into address values ( 5,'2222 forestlane','maryland','ML','39403');
-- --
-- insert into user values(1,'eyob@gmail.com','Eyob','Habtom','ey0123',0,1,1,2);
-- insert into user values(2,'bini@gmail.com','Biniam','Tshaye','bi0234',0,2,1,2);
-- insert into user values(3,'dawit@gmail.com','Dawit','tesfahanes','da0567',0,3,1,2);
-- insert into user values(4,'essey@gmail.com','Essey','Abreham','es0789',0,1,1,2);
-- insert into user values(5,'wegahta@gmail.com','Wegahta','Gerbremariam','we0213',0,3,1,2);
--
-- --
-- -- SET foreign_key_checks = 0;
insert into category values(1,'ELECTRONICS');
insert into category values(2,'CARS');
insert into category values(3,'FURNITURE');
-- --
-- -- SET foreign_key_checks = 0;
-- insert into product_categories values(1,1);
-- insert into product_categories values(2,2);
-- insert into product_categories values(3,3);
-- --
-- -- SET foreign_key_checks = 0;
-- insert into bid_user values(1,300,1);
-- insert into bid_user values(2,10000,2);
-- -- --
-- -- --
-- -- SET foreign_key_checks = 0;
-- insert into deposit_payment values(1,90,2,4);
-- insert into deposit_payment values(2,200,3,5);
-- -- --
-- -- -- SET foreign_key_checks = 0;
-- -- -- insert into product_image_link values(1,'main/resource/images');
-- -- -- insert into product_image_link values(2,'main/resource/images');
-- --
-- --
-- -- -- SET foreign_key_checks = 0;
-- -- -- insert into bid values(1,'2020-03-02',null);
-- -- -- insert into bid values(2,'2020-05-02',null);
-- -- -- -- insert into bid values(3,'2019-05-02',3);
-- -- --
-- -- -- SET foreign_key_checks = 0;
-- -- -- insert into role values(1,'ROLE_ADMIN');
-- -- -- insert into role values (2,'ROLE_SELLER');
-- -- -- insert into role values (3,'ROLE_CUSTOMER');
-- -- --
-- -- -- SET foreign_key_checks = 0;
-- -- -- insert into user values(1,'eyob@gmail.com','bini','Habtom','ey0123',0,null,1,2);
-- -- --
-- -- -- -- SET foreign_key_checks = 0;
-- -- -- -- insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',null);
-- -- -- -- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- -- -- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- -- -- --
-- -- -- -- insert into credentials values ( 1,'eyob' ,'eyob');
-- -- -- SET foreign_key_checks = 0;
-- -- -- insert into credentials values ( 1,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'essey');
-- -- -- -- insert into credentials values ( 2,'dawit' ,'dawit');

-- -- -- -- insert into credentials values ( 4,'essey' ,'essey');
-- -- -- -- insert into credentials values ( 5,'wegahta' ,'wegahta');
-- -- -- -- -- SET foreign_key_checks = 0;
insert into address values ( 1,'philadelphia','PA','7520greenhillrd','19151');
insert into address values ( 2,'fairfield','IA','2000 N court','52556');
insert into address values ( 3,'01301 Abrahams road','dallas','TX','75243');
insert into address values ( 4,'1230 mainstreet','washignton','DC','20039');
insert into address values ( 5,'2222 forestlane','maryland','ML','39403');
-- --
SET foreign_key_checks = 0;
-- insert into bid values(1,1);
-- insert into bid values(2,2);
-- insert into bid values(3,3);

insert into role values(1,'ROLE_ADMIN');
insert into role values (2,'ROLE_SELLER');
insert into role values (3,'ROLE_CUSTOMER');
SET foreign_key_checks = 0;
insert into user values(1,'binireyes@gmail.com','bini','Habtom','ey0123',1,1,1,1,1);
insert into user values(2,'eyob@gmail.com','essey','Habtom','ey0123',1,1,2,2,2);
insert into user values(3,'eyob@gmail.com','eyob','Habtom','ey0123',1,1,3,3,2);
-- insert into user values(4,'eyob@gmail.com','wegih','Habtom','ey0123',1,1,4,3,3);
-- insert into user values(5,'eyob@gmail.com','dawit','Habtom','ey0123',1,1,5,3,3);


-- SET foreign_key_checks = 0;
insert into product values(1,1,34.00,'perfect product','2020-03-20',45000.00,0,'2020-03-03','car',0,'Yes',0,1,40000.00,'2020-03-03',2);
insert into product values(2,0,20.00,'perfect product','2020-03-10',20000.00,0,'2020-03-03','car',0,'Yes',0,0,20000.00,'2020-03-03',2);
insert into product values(3,0,20.00,'perfect product','2020-03-10',20000.00,0,'2020-03-03','car',0,'Yes',0,0,20000.00,'2020-03-03',3);
-- insert into bid values(1,1);
-- insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
-- insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
SET foreign_key_checks = 0;
-- insert into credentials values ( 1,'eyob' ,'eyob');
insert into credentials values ( 1,'$2a$10$xRx8xiDeumkhJz5SYeIzxujH3Z78fjLjSbMTGhN1j0xstTF3zJojC' ,'bini');
insert into credentials values ( 2,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'essey');
insert into credentials values ( 3,'$2a$10$7HLAc9IuKNUC6T.TK4teou8IG9BxVDdYxgVa3QGPtBPeiaxGZspSO' ,'eyob');
-- insert into credentials values ( 4,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'wegih');
-- insert into credentials values ( 5,'$2a$10$xgZo5DsCs3j4Z9nHqeIc0u.Tsm5UADlSuwTH32lww/rJMZSSYhXTu' ,'dawit');
insert into product_photos values ( 1,'/images/product-photos/2/322868_1100-1100x628.jpg');
insert into product_photos values ( 2,'/images/product-photos/3/322868_1100-1100x628.jpg');
insert into product_photos values ( 3,'/images/product-photos/3/download.jpeg');
-- insert into USER_WON_PRODUCTS values ( 1,1 );
-- insert into USER_WON_PRODUCTS values ( 2,2 );
-- insert into USER_WON_PRODUCTS values ( 3,3 );

-- insert into bid_history values(1,400.00,'2021-01-02',1,3);
-- insert into deposit_payment values (1,'2021-01-02','5678908765378965','323',34.00,0,'essey','2020-10-12',2,3);
insert into BID_USER values ( 1,45000.00,3 );