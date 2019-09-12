package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Study;
import com.prj.quiz.model.filter.StudyFilter;

import java.util.List;

public interface StudyRepository {
    Study getById(Integer id);

    List<Study> getAll(StudyFilter studyFilter);

    int save(Study study);

    int update(Study study);

    int delete(Integer id);
}
