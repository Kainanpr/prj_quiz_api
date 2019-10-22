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
(6, 1, 6, 1),

(7, 2, 1, 1),
(8, 2, 2, 1),
(9, 2, 3, 1),
(10, 2, 4, 1),
(11, 2, 5, 1),
(12, 2, 6, 1),

(13, 3, 1, 1),
(14, 3, 2, 1),
(15, 3, 3, 1),
(16, 3, 4, 1),
(17, 3, 5, 1),
(18, 3, 6, 1),

(19, 4, 1, 1),
(20, 4, 2, 1),
(21, 4, 3, 1),
(22, 4, 4, 1),
(23, 4, 5, 1),
(24, 4, 6, 1),

(25, 5, 1, 1),
(26, 5, 2, 1),
(27, 5, 3, 1),
(28, 5, 4, 1),
(29, 5, 5, 1),
(30, 5, 6, 1);
