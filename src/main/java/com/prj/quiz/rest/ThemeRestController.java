package com.prj.quiz.rest;

import com.prj.quiz.model.Content;
import com.prj.quiz.model.Theme;
import com.prj.quiz.rest.dto.read.ContentReadDto;
import com.prj.quiz.rest.dto.read.ThemeReadDto;
import com.prj.quiz.rest.dto.write.ThemeWriteDto;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.service.ThemeService;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/themes")
public class ThemeRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeRestController.class);

    private final ThemeService themeService;

    public ThemeRestController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ThemeReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return theme: {}", id);

        try {
            final Theme theme = themeService.getById(id);
            final ThemeReadDto dto = toThemeReadDto(theme);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private ThemeReadDto toThemeReadDto(Theme theme) {
        return new ThemeReadDto.Builder()
                .setId(theme.getId())
                .setName(theme.getName())
                .setContents(toContentReadDto(theme.getContents()))
                .build();
    }

    private List<ContentReadDto> toContentReadDto(List<Content> contents) {
        final List<ContentReadDto> contentReadDto = new ArrayList<>();

        for (Content content : contents) {
            contentReadDto.add(new ContentReadDto.Builder()
                    .setId(content.getId())
                    .setName(content.getName())
                    .setThemeId(content.getThemeId())
                    .build());
        }

        return contentReadDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ThemeReadDto>> getAll(CommonFilter commonFilter) {
        LOGGER.info("Parameters received to filter: {}", commonFilter);

        final List<Theme> themeList = themeService.getAll(commonFilter);
        final List<ThemeReadDto> responseBody = new ArrayList<>(themeList.size());

        for (Theme theme : themeList) {
            responseBody.add(toThemeReadDto(theme));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ThemeReadDto> create(@RequestBody @Valid ThemeWriteDto themeWrite) {
        LOGGER.info("Theme received to save: {}", themeWrite);

        final Theme theme = toThemeModel(null, themeWrite);
        final Theme savedTheme = themeService.save(theme);

        final ThemeReadDto responseBody = toThemeReadDto(savedTheme);
        return ResponseEntity
                .created(URI.create("/v1/themes/" + savedTheme.getId()))
                .body(responseBody);
    }

    private Theme toThemeModel(Integer id, ThemeWriteDto themeWrite) {
        return new Theme.Builder()
                .setId(id)
                .setName(themeWrite.getName())
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ThemeReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid ThemeWriteDto themeWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Theme received to update: {}", themeWrite);

        final Theme theme = toThemeModel(id, themeWrite);

        try {
            final Theme updatedTheme = themeService.update(theme);

            final ThemeReadDto responseBody = toThemeReadDto(updatedTheme);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseBody);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);

        try {
            themeService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
