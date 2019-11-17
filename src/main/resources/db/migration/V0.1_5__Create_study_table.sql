create table study (
  id int not null auto_increment primary key,
  word text not null,
  translation text not null,
  content_id int not null,
  level_id int not null,

  constraint study_fk_content foreign key (content_id) references content (id),
  constraint study_fk_level foreign key (level_id) references level (id)
);

insert into study (id, word, translation, content_id, level_id) values
(1, 'Dent', 'Mossa', 2, 1),
(2, 'Nicks', 'Cortes', 2, 1),
(3, 'Scratches', 'Arranhões', 2, 1),
(4, 'Cracks', 'Rachaduras', 2, 1),
(5, 'Holes', 'Perfurações', 2, 1),
(6, 'Abrasion', 'Abrasão', 2, 1),
(7, 'Gouge', 'Ranhura', 2, 1),
(8, 'Corrosion', 'Corrosão', 2, 1),
(9, 'Delamination', 'Delaminação', 2, 1),
(10, 'Disbond', 'Decolar', 2, 1);
