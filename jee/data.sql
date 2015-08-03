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