create table auth
(
	id int null,
	code_ver varchar(500) null,
	state int default '0' null
)
;

create table bookclaim
(
	firstname varchar(200) null,
	lastname varchar(200) null,
	email varchar(200) null,
	message varchar(200) null
)
;

create table inventory
(
	bookid int auto_increment
		primary key,
	isbn_number varchar(20) null,
	book_title varchar(20) null,
	book_type varchar(20) not null,
	department varchar(20) null,
	auther_name varchar(20) not null,
	edition varchar(20) not null,
	quantity int null,
	price varchar(20) null,
	publisher varchar(20) null,
	reserved_quantity int default '0' null,
	constraint isbn_number
		unique (isbn_number)
)
;

create table reserve_register
(
	reserve_id int auto_increment
		primary key,
	bookid int null,
	userid_reserved varchar(20) null,
	issue_date date null,
	due_date date null,
	return_date date null,
	constraint reserve_register_ibfk_1
		foreign key (bookid) references inventory (bookid)
)
;

create index bookid
	on reserve_register (bookid)
;

create table special_reserve_register
(
	sp_reserve_id int auto_increment
		primary key,
	bookid int null,
	userid_reserved varchar(20) null,
	issue_date date null,
	due_date date null,
	return_date date null,
	quantity int null,
	constraint special_reserve_register_ibfk_1
		foreign key (bookid) references inventory (bookid)
)
;

create index bookid
	on special_reserve_register (bookid)
;

create table user
(
	id int(10) auto_increment
		primary key,
	username varchar(200) null,
	fname varchar(200) null,
	lname varchar(200) null,
	email varchar(200) null,
	password varchar(200) null,
	role varchar(200) null
)
;

create table waitlist_register
(
	waitlist_id int auto_increment
		primary key,
	bookid int null,
	userid_reserved varchar(20) null,
	requested_date date null,
	wait_list_no int null,
	current_wait_list_no int null,
	constraint waitlist_register_ibfk_1
		foreign key (bookid) references inventory (bookid)
)
;

create index bookid
	on waitlist_register (bookid)
;

