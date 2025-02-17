  CREATE DATABASE db_auth;
  
  use db_auth;
  CREATE TABLE tbl_user(
  	id_user integer      not null auto_increment primary key,
      login varchar(100) not null,
      password varchar(200) not null,
      name varchar(255) not null,
      email varchar(255) not null,
      role int not null
  );
  
  desc tbl_user;
  
  alter table tbl_user add column role int not null;
  
  update tbl_user set role = 0 where id_user =1;
  
  drop table tbl_user;
  
  select * from tbl_user;
