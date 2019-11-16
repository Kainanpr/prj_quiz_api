package com.prj.quiz.rest;

import com.prj.quiz.model.Study;
import com.prj.quiz.rest.dto.read.StudyReadDto;
import com.prj.quiz.rest.dto.write.StudyWriteDto;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.service.StudyService;
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
@RequestMapping("/v1/studies")
public class StudyRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudyRestController.class);

    private final StudyService studyService;

    public StudyRestController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudyReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return study: {}", id);

        try {
            final Study study = studyService.getById(id);
            final StudyReadDto dto = toStudyReadDto(study);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private StudyReadDto toStudyReadDto(Study study) {
        return new StudyReadDto.Builder()
                .setId(study.getId())
                .setWord(study.getWord())
                .setTranslation(study.getTranslation())
                .setContentId(study.getContentId())
                .setLevelId(study.getLevelId())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StudyReadDto>> getAll(CommonFilter commonFilter) {
        LOGGER.info("Parameters received to filter: {}", commonFilter);

        final List<Study> studyList = studyService.getAll(commonFilter);
        final List<StudyReadDto> responseBody = new ArrayList<>(studyList.size());

        for (Study study : studyList) {
            responseBody.add(toStudyReadDto(study));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudyReadDto> create(@RequestBody @Valid StudyWriteDto studyWrite) {
        LOGGER.info("Study received to save: {}", studyWrite);

        final Study study = toStudyModel(null, studyWrite);
        final Study savedStudy = studyService.save(study);

        final StudyReadDto responseBody = toStudyReadDto(savedStudy);
        return ResponseEntity
                .created(URI.create("/v1/studies/" + savedStudy.getId()))
                .body(responseBody);
    }

    private Study toStudyModel(Integer id, StudyWriteDto studyWrite) {
        return new Study.Builder()
                .setId(id)
                .setWord(studyWrite.getWord())
                .setTranslation(studyWrite.getTranslation())
                .setContentId(studyWrite.getContentId())
                .setLevelId(studyWrite.getLevelId())
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudyReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid StudyWriteDto studyWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Study received to update: {}", studyWrite);

        final Study study = toStudyModel(id, studyWrite);

        try {
            final Study updatedStudy = studyService.update(study);

            final StudyReadDto responseBody = toStudyReadDto(updatedStudy);
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
            studyService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
