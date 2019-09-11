create table level (
  id int not null auto_increment primary key,
  name varchar(50) not null
);

insert into level (id, name) values
(1, 'Iniciante'),
(2, 'Básico'),
(3, 'Intermediário'),
(4, 'Avançado');
