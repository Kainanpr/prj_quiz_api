package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Content;
import com.prj.quiz.rest.filter.CommonFilter;

import java.util.List;

public interface ContentRepository {
    Content getById(Integer id);

    List<Content> getAll(Integer themeId, CommonFilter commonFilter);

    int save(Content content);

    int update(Content content);

    int delete(Integer id);
}
