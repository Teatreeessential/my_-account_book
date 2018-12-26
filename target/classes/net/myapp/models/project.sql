
/* Drop Tables */

DROP TABLE tbl_balance CASCADE CONSTRAINTS;
DROP TABLE tbl_login CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE tbl_balance
(
	bank_name varchar2(30) NOT NULL,
	fintech_use_num varchar2(100) NOT NULL,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (fintech_use_num, bank_name),
	constraint fk_balance_id foreign key (id) references tbl_login(id) on delete cascade
);


CREATE TABLE tbl_login
(
	id varchar2(20) NOT NULL,
	passwd varchar2(200) NOT NULL,
	access_token varchar2(40) NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE tbl_balance
	ADD FOREIGN KEY (id)
	REFERENCES tbl_login (id)
;

insert into tbl_login values ('abcd','1111','14311d18-fadd-42b3-8159-b98c81925764')
insert into tbl_balance values('신한은행','199000840057721775623729','abcd')
insert into tbl_balance values('우리은행','199000840057721775635458','abcd')


commit

		select mem.id,mem.sex,mem.access_token,fin.fintech_use_num
		from  
		(select * from tbl_member where id='abc' and passwd='1111') mem, tbl_fintechnum fin
		where mem.id = fin.id
