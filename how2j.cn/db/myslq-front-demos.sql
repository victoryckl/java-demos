create database how2java;

CREATE TABLE hero (
  id int(11) AUTO_INCREMENT,
  name varchar(30) ,
  hp float ,
  damage int(11) ,
  PRIMARY KEY (id)
)  DEFAULT CHARSET=utf8;

insert into hero values (null, '盖伦', 616, 100);
insert into hero values (null, 'hero 0', 626, 100);
insert into hero values (null, 'hero 1', 636, 99);
insert into hero values (null, 'hero 2', 646, 98);
insert into hero values (null, 'hero 3', 656, 97);
insert into hero values (null, 'hero 4', 666, 96);
insert into hero values (null, 'hero 5', 676, 95);
insert into hero values (null, 'hero 6', 686, 94);
insert into hero values (null, 'hero 7', 696, 93);
insert into hero values (null, 'hero 8', 706, 92);
insert into hero values (null, 'hero 9', 710, 91);
insert into hero values (null, 'hero 10', 726, 90);
insert into hero values (null, 'hero 11', 736, 89);

select * from hero;

select count(*) from hero;

select * from hero limit 0,5;
select * from hero limit 5,5;

update hero set hp = 818 where id = 1;
delete from hero where id = 1;

