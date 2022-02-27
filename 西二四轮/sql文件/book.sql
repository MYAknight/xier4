drop table bookbase;
create table if not exists bookbase(
book_id int(60) primary key auto_increment,
name varchar(60),
intro varchar(200),
front varchar(200),
content varchar(200),
type varchar(20),
xianshi int(20),
set_time timestamp not null default current_timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO bookbase(name , intro ,front ,content ,type ,xianshi  )
                       VALUES
                       ( "examplebook2","book1book1book1book1book1book1book1book1","bookStore/pageImage/p0001.png","bookStore/bookContent/b0001.txt","A",1);
INSERT INTO bookbase(name , intro ,front ,content ,type ,xianshi  )
                       VALUES
                       ( "examplebook2","book2book2book2book2book2book2book2book2","bookStore/pageImage/p0002.png","bookStore/bookContent/b0002.txt","A",1);
INSERT INTO bookbase(name , intro ,front ,content ,type ,xianshi  )
                       VALUES
                       ( "examplebook2","book3book3book3book3book3book3book3book3","bookStore/pageImage/p0003.png","bookStore/bookContent/b0003.txt","B",1);
