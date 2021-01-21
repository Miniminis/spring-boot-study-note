insert into person(`id`, `name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`)
    values (1, 'martin', 10, 'A',  1994, 8, 11);
insert into person(`id`, `name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`)
    values (2, 'james', 12, 'B',  2000, 8, 27);
insert into person(`id`, `name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`)
    values (3, 'katy', 10, 'O',  1988, 10, 9);
insert into person(`id`, `name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`)
    values (4, 'selly', 10, 'B',  1950, 12, 12);
insert into person(`id`, `name`, `age`, `blood_type`, `year_of_birthday`, `month_of_birthday`, `day_of_birthday`)
    values (5, 'martin', 10, 'AB',  2020, 12, 31);

insert into block (`id`, `name`) values (1, 'james');

update person set block_id = 1 where id = 1;
update person set block_id = 1 where id = 4;
