USE cpkDb;
 CREATE TABLE pitems(
	pid int primary key,
	pname varchar(20) not null,
	pdesc varchar(30) not null,
    cost decimal not null
);


INSERT INTO pitems VALUES
(101,"Face-Mask","Good quality face mask - n97", 100.00),
(102,"Sanitize","99.5% germ protection", 300.00);
