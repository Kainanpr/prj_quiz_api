package com.prj.quiz.service;

import com.prj.quiz.model.Test;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.persistence.repository.TestRepository;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test getById(Integer id) {
        final Test test = testRepository.getById(id);

        if (test == null) {
            throw new ObjectNotFoundException("Test not found! ID: " + id);
        }

        LOGGER.info("Retrieved test by ID: {}", test);
        return test;
    }

    public List<Test> getAll(CommonFilter commonFilter) {
        final List<Test> testList = testRepository.getAll(commonFilter);
        LOGGER.info("Retrieved all tests: {}", testList);
        return testList;
    }

    @Transactional
    public Test save(Test test) {
        final int savedId = testRepository.save(test);

        final Test savedTest = testRepository.getById(savedId);
        LOGGER.info("Saved Test: {}", savedTest);
        return savedTest;
    }

    @Transactional
    public Test update(Test test) {
        final int testId = test.getId();
        final int affectedRows = testRepository.update(test);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find test with id (not updated): " + testId);
        }

        final Test updatedTest = testRepository.getById(testId);
        LOGGER.info("Updated Test: {}", updatedTest);
        return updatedTest;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = testRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find test with id (not deleted): " + id);
        }

        LOGGER.info("Deleted test (deleted rows: {})", affectedRows);
    }
}
