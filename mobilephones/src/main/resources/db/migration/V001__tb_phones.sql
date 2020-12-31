CREATE TABLE tb_phones(
	code varchar(36) NOT NULL,
	brand varchar(100) NOT NULL,
    date varchar(50) NOT NULL,
    model varchar(100),
    photo varchar(250) NOT NULL,
    price int not null,
	PRIMARY KEY(code)
) engine=InnoDB default charset=utf8;