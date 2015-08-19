drop table if exists books;
create table books(
	ID INTEGER PRIMARY KEY AUTO_INCREMENT,
	name varchar(50) not null,
	isbn varchar(50) not null,
	category varchar(50) not null
)