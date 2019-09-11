package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.Level;

import java.util.List;

public interface LevelRepository {
    List<Level> getAll();
}
