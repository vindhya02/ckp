DROP DATABASE cpkDb;

CREATE DATABASE cpkDb;

USE cpkDb;


CREATE TABLE citems(
	userid int primary key,
	username varchar(20) not null,
	email varchar(20) not null,
    address varchar(20),
    contact varchar(20) not null
);

select * from citems;

INSERT INTO citems VALUES
(1,"Penny","penny@gmail.com","hyderabad","9087654343"),
(2,"Jenny","Jenny@gmail.com","hyderabad","9087654343"),
(3,"Benny","Benny@gmail.com","","9087654343");