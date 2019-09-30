package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Study;
import com.prj.quiz.rest.filter.CommonFilter;

import java.util.List;

public interface StudyRepository {
    Study getById(Integer id);

    List<Study> getAll(CommonFilter commonFilter);

    int save(Study study);

    int update(Study study);

    int delete(Integer id);
}
