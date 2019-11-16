package com.prj.quiz.rest;

import com.prj.quiz.model.Content;
import com.prj.quiz.model.Theme;
import com.prj.quiz.rest.dto.read.ContentReadDto;
import com.prj.quiz.rest.dto.write.ContentWriteDto;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.service.ContentService;
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
@RequestMapping("/v1/contents")
public class ContentRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentRestController.class);

    private final ContentService contentService;

    public ContentRestController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return content: {}", id);

        try {
            final Content content = contentService.getById(id);
            final ContentReadDto dto = toContentReadDto(content);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private ContentReadDto toContentReadDto(Content content) {
        return new ContentReadDto.Builder()
                .setId(content.getId())
                .setName(content.getName())
                .setTheme(content.getTheme())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContentReadDto>> getAll(Integer themeId, CommonFilter commonFilter) {
        LOGGER.info("themeId received to filter: {}", themeId);
        LOGGER.info("Parameters received to filter: {}", commonFilter);

        final List<Content> contentList = contentService.getAll(themeId, commonFilter);
        final List<ContentReadDto> responseBody = new ArrayList<>(contentList.size());

        for (Content content : contentList) {
            responseBody.add(toContentReadDto(content));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentReadDto> create(@RequestBody @Valid ContentWriteDto contentWrite) {
        LOGGER.info("Content received to save: {}", contentWrite);

        final Content content = toContentModel(null, contentWrite);
        final Content savedContent = contentService.save(content);

        final ContentReadDto responseBody = toContentReadDto(savedContent);
        return ResponseEntity
                .created(URI.create("/v1/contents/" + savedContent.getId()))
                .body(responseBody);
    }

    private Content toContentModel(Integer id, ContentWriteDto contentWrite) {
        Theme theme = new Theme.Builder()
                .setId(contentWrite.getThemeId())
                .build();

        return new Content.Builder()
                .setId(id)
                .setName(contentWrite.getName())
                .setTheme(theme)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid ContentWriteDto contentWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Content received to update: {}", contentWrite);

        final Content content = toContentModel(id, contentWrite);

        try {
            final Content updatedContent = contentService.update(content);

            final ContentReadDto responseBody = toContentReadDto(updatedContent);
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
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to delete: {}", id);

        try {
            contentService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
