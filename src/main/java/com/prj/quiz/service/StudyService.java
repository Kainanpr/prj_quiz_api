package com.prj.quiz.service;

import com.prj.quiz.model.Study;
import com.prj.quiz.model.filter.CommonFilter;
import com.prj.quiz.persistence.repository.StudyRepository;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudyService.class);

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Study getById(Integer id) {
        final Study study = studyRepository.getById(id);

        if (study == null) {
            throw new ObjectNotFoundException("Study not found! ID: " + id);
        }

        LOGGER.info("Retrieved study by ID: {}", study);
        return study;
    }

    public List<Study> getAll(CommonFilter commonFilter) {
        final List<Study> studyList = studyRepository.getAll(commonFilter);
        LOGGER.info("Retrieved all studies: {}", studyList);
        return studyList;
    }

    @Transactional
    public Study save(Study study) {
        final int savedId = studyRepository.save(study);

        final Study savedStudy = studyRepository.getById(savedId);
        LOGGER.info("Saved Study: {}", savedStudy);
        return savedStudy;
    }

    @Transactional
    public Study update(Study study) {
        final int studyId = study.getId();
        final int affectedRows = studyRepository.update(study);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find study with id (not updated): " + studyId);
        }

        final Study updatedStudy = studyRepository.getById(studyId);
        LOGGER.info("Updated Study: {}", updatedStudy);
        return updatedStudy;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = studyRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find study with id (not deleted): " + id);
        }

        LOGGER.info("Deleted study (deleted rows: {})", affectedRows);
    }
}
