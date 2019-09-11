package com.prj.quiz.rest;

import com.prj.quiz.model.Level;
import com.prj.quiz.rest.dto.read.LevelReadDto;
import com.prj.quiz.service.LevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/levels")
public class LevelRestController {
    private final LevelService levelService;

    public LevelRestController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<LevelReadDto>> getAll() {
        final List<Level> levelList = levelService.getAll();
        final List<LevelReadDto> responseBody = new ArrayList<>(levelList.size());

        for (Level level : levelList) {
            responseBody.add(toLevelReadDto(level));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    private LevelReadDto toLevelReadDto(Level level) {
        return new LevelReadDto.Builder()
                .setId(level.getId())
                .setName(level.getName())
                .build();
    }
}
