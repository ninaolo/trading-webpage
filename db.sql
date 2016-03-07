use ninaolo;

drop table securities;
drop table orders;
drop table trades;

create table securities (
	id int NOT NULL AUTO_INCREMENT,
       name varchar(64),
	PRIMARY KEY (id)
);

create table orders (
	id int NOT NULL AUTO_INCREMENT,
       name varchar(64),
       type varchar(64),
       price float,
       amount int,
       uid int,
	PRIMARY KEY (id)
);

create table trades (
	id int NOT NULL AUTO_INCREMENT,
       name varchar(64),
       price float,
       amount int,
       dt datetime,
       buyer int,
       seller int,
	PRIMARY KEY (id)
);


create table users (
       id int NOT NULL AUTO_INCREMENT,
       name varchar(64),
       email varchar(64),
       PRIMARY KEY (id)
);