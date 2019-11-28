create table test (
  id int not null auto_increment primary key,
  question text not null,
  option_1 text not null,
  option_2 text not null,
  option_3 text not null,
  option_4 text not null,
  option_5 text not null,
  answer text not null,
  content_id int not null,
  level_id int not null,

  constraint test_fk_content foreign key (content_id) references content (id),
  constraint test_fk_level foreign key (level_id) references level (id)
);
