package com.prj.quiz.rest;

import com.prj.quiz.model.Game;
import com.prj.quiz.rest.dto.read.GameReadDto;
import com.prj.quiz.rest.dto.write.GameWriteDto;
import com.prj.quiz.service.GameService;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/games")
public class GameRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameRestController.class);

    private final GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GameReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return game: {}", id);

        try {
            final Game game = gameService.getById(id);
            final GameReadDto dto = toGameReadDto(game);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private GameReadDto toGameReadDto(Game game) {
        return new GameReadDto.Builder()
                .setId(game.getId())
                .setUserId(game.getUserId())
                .setContentId(game.getContentId())
                .setLevelId(game.getLevelId())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<GameReadDto>> getAll(Integer userId, Integer contentId) {
        final List<Game> gameList = gameService.getAll(userId, contentId);
        final List<GameReadDto> responseBody = new ArrayList<>(gameList.size());

        for (Game game : gameList) {
            responseBody.add(toGameReadDto(game));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GameReadDto> create(@RequestBody @Valid GameWriteDto gameWrite) {
        LOGGER.info("Game received to save: {}", gameWrite);

        final Game game = toGameModel(null, gameWrite);
        final Game savedGame = gameService.save(game);

        final GameReadDto responseBody = toGameReadDto(savedGame);
        return ResponseEntity
                .created(URI.create("/v1/games/" + savedGame.getId()))
                .body(responseBody);
    }

    private Game toGameModel(Integer id, GameWriteDto gameWrite) {
        return new Game.Builder()
                .setId(id)
                .setUserId(gameWrite.getUserId())
                .setContentId(gameWrite.getContentId())
                .setLevelId(gameWrite.getLevelId())
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GameReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid GameWriteDto gameWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Game received to update: {}", gameWrite);

        final Game game = toGameModel(id, gameWrite);

        try {
            final Game updatedGame = gameService.update(game);

            final GameReadDto responseBody = toGameReadDto(updatedGame);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseBody);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Integer contentId) {
        LOGGER.info("ID received to delete: {}", contentId);

        try {
            gameService.delete(contentId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
