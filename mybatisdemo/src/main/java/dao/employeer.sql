use testdb;
create table t_employeer(
employeer_id int not null  primary key AUTO_INCREMENT ,
employeer_name varchar(50) default null,
employeer_age int default null,
employeer_department varchar(100) default null,
employeer_worktype varchar(100) default null
)