create table person (
    id long primary key auto_increment,
    name varchar(20),
    age int,
    address varchar(255)
);

insert into person (name, age, address) values ('홍길동', 12, '서울시 영등로구');
insert into person (name, age, address) values ('도우너', 35, '서울시 마포구');
insert into person (name, age, address) values ('희동이', 101, '서울시 중구');
insert into person (name, age, address) values ('마이콜', 7, '서울시 종로구');