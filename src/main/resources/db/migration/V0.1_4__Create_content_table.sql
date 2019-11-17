create table content (
  id int not null auto_increment primary key,
  name varchar(100) not null,
  theme_id int not null,

  constraint content_fk_theme foreign key (theme_id) references theme (id)
);

insert into content (id, name, theme_id) values
(1, 'Partes da Aeronave', 1),
(2, 'Danos', 1),
(3, 'Características Técnicas', 1),
(4, 'Modais', 2),
(5, 'Presente Simples', 2),
(6, 'Elementos de Referência', 2);
