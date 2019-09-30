package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Test;
import com.prj.quiz.rest.filter.CommonFilter;

import java.util.List;

public interface TestRepository {
    Test getById(Integer id);

    List<Test> getAll(CommonFilter commonFilter);

    int save(Test test);

    int update(Test test);

    int delete(Integer id);
}
