drop table if exists STATUS;
CREATE TABLE STATUS
(
    STATUSID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    STATUSNAME VARCHAR(20) NOT NULL
);

drop table if exists FOOD_TYPE;
CREATE TABLE FOOD_TYPE
(
    FOODTYPEID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    FOODTYPENAME VARCHAR(20) NOT NULL
);

drop table if exists FOOD_PACKAGE;
CREATE TABLE FOOD_PACKAGE
(
    FOODID INT NOT NULL,
    PACKAGEID INT NOT NULL,
    PRIMARY KEY (FOODID, PACKAGEID)
);

drop table if exists PACKAGE;
CREATE TABLE PACKAGE
(
    PACKAGEID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    PACKAGENAME VARCHAR(20) NOT NULL,
    PRICE DOUBLE NOT NULL,
    STATUSID INTEGER NOT NULL,
    USERID INTEGER NOT NULL
);

drop table if exists food;
CREATE TABLE FOOD
(
    FOODID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    FOODNAME VARCHAR(20) NOT NULL,
    PRICE DOUBLE NOT NULL,
    PICTURE_URL VARCHAR(200),
    STATUSID INTEGER NOT NULL,
    FOODTYPEID INTEGER NOT NULL,
    USERID INTEGER NOT NULL
);

drop table if exists users;
CREATE TABLE USERS
(
    USERID INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL,
    IDCARD VARCHAR(18),
    LICENSE VARCHAR(100),
    TEL VARCHAR(20),
    ROLE VARCHAR(20) NOT NULL,
    STATUSID INTEGER NOT NULL
);

insert into status (statusname) values ('approved');
insert into status (statusname) values ('approving');
insert into status (statusname) values ('deleted');
insert into status (statusname) values ('normal');

insert into users (username,password,idcard,tel,role,statusid) values ('admin','admin','12345678','13300000000','admin',1);
insert into users (username,password,idcard,tel,role,statusid) values ('kary','123','12345678','13300000000','seller',1);
insert into users (username,password,idcard,tel,role,statusid) values ('kevin','123','12345678','13300000000','seller',1);
insert into users (username,password,idcard,tel,role,statusid) values ('jason','123','12345678','13300000000','seller',1);
insert into users (username,password,idcard,tel,role,statusid) values ('vera','123','12345678','13300000000','customer',1);
insert into users (username,password,idcard,tel,role,statusid) values ('willson','123','12345678','13300000000','customer',1);
insert into users (username,password,idcard,tel,role,statusid) values ('james','123','12345678','13300000000','customer',1);

insert into FOOD_TYPE (foodtypename) values ('��ʳ');
insert into FOOD_TYPE (foodtypename) values ('���');
insert into FOOD_TYPE (foodtypename) values ('����');

insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����1','10',1,1,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����2','20',1,1,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����3','30',1,1,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����4','40',1,1,3);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('���1','10',1,2,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('���2','20',1,2,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('���3','30',1,2,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('���4','40',1,2,3);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����1','10',1,3,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����2','20',1,3,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����3','30',1,3,2);
insert into food (foodname,price,STATUSID,FOODTYPEID,userid) values ('����4','40',1,3,3);

