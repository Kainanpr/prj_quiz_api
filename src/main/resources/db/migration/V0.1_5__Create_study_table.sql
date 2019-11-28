create table study (
  id int not null auto_increment primary key,
  word text not null,
  translation text not null,
  content_id int not null,
  level_id int not null,

  constraint study_fk_content foreign key (content_id) references content (id),
  constraint study_fk_level foreign key (level_id) references level (id)
);
