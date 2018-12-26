
/* Drop Tables */

DROP TABLE tbl_fintechnum CASCADE CONSTRAINTS;
DROP TABLE tbl_member CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE tbl_fintechnum
(
	fintech_use_num varchar2(100) NOT NULL UNIQUE,
	id varchar2(20) NOT NULL
);


CREATE TABLE tbl_member
(
	id varchar2(20) NOT NULL,
	passwd varchar2(200) NOT NULL,
	access_token varchar2(40) NOT NULL,
	sex varchar2(1) NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE tbl_fintechnum
	ADD FOREIGN KEY (id)
	REFERENCES tbl_member (id)
;

alter table tbl_member modify(sex varchar2(3))


insert into tbl_member (id,passwd,access_token,sex)
values ('abc','1111','dae96f88-2980-4b2f-a600-24f467b91b84','남');

delete from tbl_member
where id='adc'

commit

insert into tbl_fintechnum (id,fintech_use_num)
values ('abc','199000840057721775635458')

insert into tbl_fintechnum (id,fintech_use_num)
values ('abc','199000840057721775623729');

select mem.id,mem.sex,mem.access_token,fin.fintech_use_num
		from  
		(select * from tbl_member where id='abc' and passwd='1111') mem, tbl_fintechnum fin
		where mem.id = fin.id
