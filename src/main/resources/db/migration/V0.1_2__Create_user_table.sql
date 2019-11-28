create table user (
  id int not null auto_increment primary key,
  name varchar(100) not null,
  email varchar(100) not null,
  password varchar(255) not null,
  is_admin bit(1) not null default 0,

  constraint user_uk_email unique key (email)
);

insert into user (id, name, email, password, is_admin) values
(1, 'Admin', 'admin', '$2a$10$bnSykDJ3YBEb6Y0B6dbez.jCXLOVGhc6cBV5fXRvtOovSkI/3qTQO', 1),
