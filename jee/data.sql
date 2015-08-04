DROP DATABASE IF EXISTS kary;
CREATE DATABASE kary DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE kary;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       INT UNSIGNED AUTO_INCREMENT KEY,
  name VARCHAR(20) NOT NULL,
  password VARCHAR(32) NOT NULL,
  sex boolean not null default true,
  birth date,
  nickname VARCHAR(32) NOT NULL,
  idcard VARCHAR(32),
  email    VARCHAR(50)
);

insert into users (name,password,sex,birth,nickname,idcard,email) values ('admin','123456',true,sysdate(),'Admin','12345678','admin@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('kary','123456',true,sysdate(),'kary','12345678','kary@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('kevin','123456',true,sysdate(),'kevin','12345678','kevin@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('james','123456',true,sysdate(),'james','12345678','james@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('jason','123456',true,sysdate(),'jason','12345678','jason@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('willson','123456',true,sysdate(),'willson','12345678','willson@qq.com');
insert into users (name,password,sex,birth,nickname,idcard,email) values ('vera','123456',false,sysdate(),'vera','12345678','vera@qq.com');