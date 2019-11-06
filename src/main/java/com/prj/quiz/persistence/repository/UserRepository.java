package com.prj.quiz.persistence.repository;

import com.prj.quiz.model.User;

import java.util.List;

public interface UserRepository {
    User getById(Integer id);

    User findByEmail(String email);

    List<User> getAll();

    int save(User user);

    int update(User user);

    int delete(Integer id);
}
