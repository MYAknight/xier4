drop table mystar;
create table if not exists mystar(
book_id int(60),
user_id int(60),
set_time timestamp not null default current_timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;