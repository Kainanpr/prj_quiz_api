create table theme (
  id int not null auto_increment primary key,
  name varchar(50) not null
);

insert into theme (id, name) values
(1, 'Vocabulário'),
(2, 'Gramática');
