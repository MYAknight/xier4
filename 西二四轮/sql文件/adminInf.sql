drop table adminlist;
create table if not exists adminlist(
admin_id int(60) primary key auto_increment,
admin_name varchar(60),
password varchar(120)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO adminlist(admin_name ,password )
                       VALUES
                       ( "ad1","E10ADC3949BA59ABBE56E057F20F883E");
