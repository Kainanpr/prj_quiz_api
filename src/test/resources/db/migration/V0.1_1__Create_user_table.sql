create table user (
  id int not null auto_increment primary key,
  name varchar(50) not null,
  email varchar(50) not null,
  password varchar(50) not null
);

insert into user (id, name, password, senha) values
(1, 'Carlos Alves', 'carlos.alves@gmail.com', '1234'),
(2, 'Tiago Santos', 'tiago.santos@gmail.com', '5678'),
(3, 'Rafael Gomes', 'rafael.gomes@gmail.com', '1234'),
(4, 'João Andrade', 'joao.andrade@gmail.com', '5678'),
(5, 'Igor Neves', 'igor.neves@gmail.com', 'i_1997');
