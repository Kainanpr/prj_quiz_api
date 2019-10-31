package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Theme;
import com.prj.quiz.rest.filter.CommonFilter;

import java.util.List;

public interface ThemeRepository {
    Theme getById(Integer id);

    List<Theme> getAll(CommonFilter commonFilter);

    int save(Theme theme);

    int update(Theme theme);

    int delete(Integer id);
}
