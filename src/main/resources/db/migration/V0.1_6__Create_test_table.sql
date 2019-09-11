create table test (
  id int not null auto_increment primary key,
  question text not null,
  option_1 varchar(255) not null,
  option_2 varchar(255) not null,
  option_3 varchar(255) not null,
  option_4 varchar(255) not null,
  option_5 varchar(255) not null,
  answer varchar(255) not null,
  content_id int not null,
  level_id int not null,

  constraint test_fk_content foreign key (content_id) references content (id),
  constraint test_fk_level foreign key (level_id) references level (id)
);

insert into test (id, question, option_1, option_2, option_3, option_4, option_5, answer, content_id, level_id) values
(1, '(UFPB) The text suggests that in summer', 'people can have a good time.', 'everything is really perfect.', 'adolescents cannot swim.', 'everyone always has a cold.', 'nobody goes to the beach.', 'adolescents cannot swim.', 2, 1),
(2, '(UFPB) In the text, the sentence “It can burn your skin” means that the sun', 'is always bad for your skin.', 'can cause skin problems.', 'helps you have a beautiful skin.', 'is never hot.', 'cannot be dangerous', 'is always bad for your skin.', 2, 1),
(3, '(UFPB) The text suggests that scientists', 'found all kinds of dinosaurs in North America.', 'concluded that all dinosaurs made nests in the ground.', 'found out a herbivorous species of dinosaurs.', 'believed some dinosaurs lived alone.', 'discovered bird and crocodile fossils in North America.', 'believed some dinosaurs lived alone.', 2, 1),
(4, '(UFPB) The text suggests that in summer', 'people can have a good time.', 'everything is really perfect.', 'adolescents cannot swim.', 'everyone always has a cold.', 'nobody goes to the beach.', 'adolescents cannot swim.', 2, 1),
(5, '(UFPB) In the text, the sentence “It can burn your skin” means that the sun', 'is always bad for your skin.', 'can cause skin problems.', 'helps you have a beautiful skin.', 'is never hot.', 'cannot be dangerous', 'is always bad for your skin.', 2, 1),
(6, '(UFPB) The text suggests that scientists', 'found all kinds of dinosaurs in North America.', 'concluded that all dinosaurs made nests in the ground.', 'found out a herbivorous species of dinosaurs.', 'believed some dinosaurs lived alone.', 'discovered bird and crocodile fossils in North America.', 'believed some dinosaurs lived alone.', 2, 1),
(7, '(UFPB) The text suggests that in summer', 'people can have a good time.', 'everything is really perfect.', 'adolescents cannot swim.', 'everyone always has a cold.', 'nobody goes to the beach.', 'adolescents cannot swim.', 2, 1),
(8, '(UFPB) In the text, the sentence “It can burn your skin” means that the sun', 'is always bad for your skin.', 'can cause skin problems.', 'helps you have a beautiful skin.', 'is never hot.', 'cannot be dangerous', 'is always bad for your skin.', 2, 1),
(9, '(UFPB) The text suggests that scientists', 'found all kinds of dinosaurs in North America.', 'concluded that all dinosaurs made nests in the ground.', 'found out a herbivorous species of dinosaurs.', 'believed some dinosaurs lived alone.', 'discovered bird and crocodile fossils in North America.', 'believed some dinosaurs lived alone.', 2, 1),
(10, '(UFPB) The text suggests that in summer', 'people can have a good time.', 'everything is really perfect.', 'adolescents cannot swim.', 'everyone always has a cold.', 'nobody goes to the beach.', 'adolescents cannot swim.', 2, 1);
