SET foreign_key_checks = 0;
insert into bid values(1,'2020-03-02',1);
insert into bid values(2,'2020-05-02',2);
insert into bid values(3,'2019-05-02',3);
insert into role values(1,'Customer');
insert into role values (2,'Admin');
insert into role values (3,'Seller');

-- SET foreign_key_checks = 0;
insert into product values(1,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
insert into product values(2,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
insert into product values(3,'perfect product','322868_1100-1100x628.jpg','car',0,0,20000,'2020-03-02',1);
--
insert into credentials values ( 1,'eyob' ,'eyob');
insert into credentials values ( 2,'biniam' ,'biniam');
insert into credentials values ( 3,'dawit' ,'dawit');
insert into credentials values ( 4,'essey' ,'essey');
insert into credentials values ( 5,'wegahta' ,'wegahta');
-- SET foreign_key_checks = 0;
insert into address values ( 1,'7520greenhillrd','philadelphia','PA','19151');
insert into address values ( 2,'2000 N court','fairfield','IA','52556');
insert into address values ( 3,'01301 Abrahams road','dallas','TX','75243');
insert into address values ( 4,'1230 mainstreet','washignton','DC','20039');
insert into address values ( 5,'2222 forestlane','maryland','ML','39403');

insert into user values(1,'eyob@gmail.com','Eyob','Habtom','ey0123','eyob',0,1,1,3);
insert into user values(2,'bini@gmail.com','Biniam','Tshaye','bi0234','biniam',0,2,2,2);
insert into user values(3,'dawit@gmail.com','Dawit','tesfahanes','da0567','dawit',0,3,3,4);
insert into user values(4,'essey@gmail.com','Essey','Abreham','es0789','essey',0,1,2,1);
insert into user values(5,'wegahta@gmail.com','Wegahta','Gerbremariam','wegahta','we0213',0,3,2,5);


-- SET foreign_key_checks = 0;
insert into category values(1,'ELECTRONICS');
insert into category values(2,'CARS');
insert into category values(3,'Furniture');
--
-- SET foreign_key_checks = 0;
insert into product_categories values(1,1);
insert into product_categories values(2,2);
insert into product_categories values(3,3);
--
-- SET foreign_key_checks = 0;
insert into bid_user values(1,300,1);
insert into bid_user values(2,10000,2);
--
--
-- SET foreign_key_checks = 0;
insert into deposit_payment values(1,90,2,4);
insert into deposit_payment values(2,200,3,5);
--
-- SET foreign_key_checks = 0;
-- insert into product_image_link values(1,'main/resource/images');
-- insert into product_image_link values(2,'main/resource/images');


