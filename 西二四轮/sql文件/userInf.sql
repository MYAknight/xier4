CREATE DATABASE  if not exists HQSK;
use HQSK;
drop table userlist;
create table if not exists userlist(
user_id int(60) primary key auto_increment,
user_name varchar(60),
password varchar(120),
phone varchar(120),
set_time timestamp not null default current_timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
