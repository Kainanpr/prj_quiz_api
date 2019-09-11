create table user (
  id int not null auto_increment primary key,
  name varchar(50) not null,
  email varchar(50) not null,
  password varchar(50) not null,
  level_id int not null default 1,

  constraint user_fk_level foreign key (level_id) references level (id)
);

insert into user (id, name, email, password) values
(1, 'Carlos Alves', 'carlos.alves@gmail.com', '1234'),
(2, 'Tiago Santos', 'tiago.santos@gmail.com', '5678'),
(3, 'Rafael Gomes', 'rafael.gomes@gmail.com', '1234'),
(4, 'João Andrade', 'joao.andrade@gmail.com', '5678'),
(5, 'Igor Neves', 'igor.neves@gmail.com', 'i_1997');
