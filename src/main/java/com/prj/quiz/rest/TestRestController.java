package com.prj.quiz.rest;

import com.prj.quiz.model.Test;
import com.prj.quiz.rest.dto.read.TestReadDto;
import com.prj.quiz.rest.dto.write.TestWriteDto;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.service.TestService;
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
@RequestMapping("/v1/tests")
public class TestRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRestController.class);

    private final TestService testService;

    public TestRestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TestReadDto> getById(@PathVariable("id") Integer id) {
        LOGGER.info("ID received to return test: {}", id);

        try {
            final Test test = testService.getById(id);
            final TestReadDto dto = toTestReadDto(test);
            return ResponseEntity.ok(dto);
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private TestReadDto toTestReadDto(Test test) {
        return new TestReadDto.Builder()
                .setId(test.getId())
                .setQuestion(test.getQuestion())
                .setOption1(test.getOption1())
                .setOption2(test.getOption2())
                .setOption3(test.getOption3())
                .setOption4(test.getOption4())
                .setOption5(test.getOption5())
                .setAnswer(test.getAnswer())
                .setContentId(test.getContentId())
                .setLevelId(test.getLevelId())
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<TestReadDto>> getAll(CommonFilter commonFilter) {
        final List<Test> testList = testService.getAll(commonFilter);
        final List<TestReadDto> responseBody = new ArrayList<>(testList.size());

        for (Test test : testList) {
            responseBody.add(toTestReadDto(test));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TestReadDto> create(@RequestBody @Valid TestWriteDto testWrite) {
        LOGGER.info("Test received to save: {}", testWrite);

        final Test test = toTestModel(null, testWrite);
        final Test savedTest = testService.save(test);

        final TestReadDto responseBody = toTestReadDto(savedTest);
        return ResponseEntity
                .created(URI.create("/v1/tests/" + savedTest.getId()))
                .body(responseBody);
    }

    private Test toTestModel(Integer id, TestWriteDto testWrite) {
        return new Test.Builder()
                .setId(id)
                .setQuestion(testWrite.getQuestion())
                .setOption1(testWrite.getOption1())
                .setOption2(testWrite.getOption2())
                .setOption3(testWrite.getOption3())
                .setOption4(testWrite.getOption4())
                .setOption5(testWrite.getOption5())
                .setAnswer(testWrite.getAnswer())
                .setContentId(testWrite.getContentId())
                .setLevelId(testWrite.getLevelId())
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TestReadDto> update(@PathVariable("id") Integer id, @RequestBody @Valid TestWriteDto testWrite) {
        LOGGER.info("ID received to update: {}", id);
        LOGGER.info("Test received to update: {}", testWrite);

        final Test test = toTestModel(id, testWrite);

        try {
            final Test updatedTest = testService.update(test);

            final TestReadDto responseBody = toTestReadDto(updatedTest);
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
            testService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (ObjectNotFoundException ex) {
            LOGGER.error("{}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
