package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Game;

import java.util.List;

public interface GameRepository {
    Game getById(Integer id);

    List<Game> getAll(Integer userId, Integer contentId);

    int save(Game game);

    int update(Game game);

    int delete(Integer contentId);
}
