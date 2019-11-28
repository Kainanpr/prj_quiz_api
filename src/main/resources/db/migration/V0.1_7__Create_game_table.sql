create table game (
  id int not null auto_increment primary key,
  user_id int not null,
  content_id int not null,
  level_id int not null,

  constraint game_fk_user foreign key (user_id) references user (id),
  constraint game_fk_content foreign key (content_id) references content (id),
  constraint game_fk_level foreign key (level_id) references level (id)
);

insert into game (id, user_id, content_id, level_id) values
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 1, 3, 1),
(4, 1, 4, 1),
(5, 1, 5, 1),
(6, 1, 6, 1);
