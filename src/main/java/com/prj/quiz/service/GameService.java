package com.prj.quiz.service;

import com.prj.quiz.model.Game;
import com.prj.quiz.persistence.repository.GameRepository;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getById(Integer id) {
        final Game game = gameRepository.getById(id);

        if (game == null) {
            throw new ObjectNotFoundException("Game not found! ID: " + id);
        }

        LOGGER.info("Retrieved game by ID: {}", game);
        return game;
    }

    public List<Game> getAll(Integer userId, Integer contentId) {
        final List<Game> gameList = gameRepository.getAll(userId, contentId);
        LOGGER.info("Retrieved all games: {}", gameList);
        return gameList;
    }

    @Transactional
    public Game save(Game game) {
        final int savedId = gameRepository.save(game);

        final Game savedGame = gameRepository.getById(savedId);
        LOGGER.info("Saved Game: {}", savedGame);
        return savedGame;
    }

    @Transactional
    public Game update(Game game) {
        final int gameId = game.getId();
        final int affectedRows = gameRepository.update(game);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find game with id (not updated): " + gameId);
        }

        final Game updatedGame = gameRepository.getById(gameId);
        LOGGER.info("Updated Game: {}", updatedGame);
        return updatedGame;
    }

    @Transactional
    public void delete(Integer contentId) {
        final int affectedRows = gameRepository.delete(contentId);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find game with contentId (not deleted): " + contentId);
        }

        LOGGER.info("Deleted game (deleted rows: {})", affectedRows);
    }
}
