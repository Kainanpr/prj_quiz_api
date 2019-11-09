package com.prj.quiz.service;

import com.prj.quiz.model.Content;
import com.prj.quiz.model.Game;
import com.prj.quiz.model.User;
import com.prj.quiz.persistence.repository.UserRepository;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final GameService gameService;
    private ContentService contentService;


    public UserService(UserRepository userRepository, GameService gameService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    // Using Setter-based Dependency Injection to allow circular dependency (UserService has ContentService and vice versa)
    @Autowired
    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    public User getById(Integer id) {
        final User user = userRepository.getById(id);

        if (user == null) {
            throw new ObjectNotFoundException("User not found! ID: " + id);
        }

        LOGGER.info("Retrieved user by ID: {}", user);
        return user;
    }

    public User login(String email, String password) {
        final User user = userRepository.login(email, password);

        if (user == null) {
            throw new ObjectNotFoundException("User not found! login: " + email);
        }

        LOGGER.info("Retrieved user by login: {}", user);
        return user;
    }

    public List<User> getAll() {
        final List<User> userList = userRepository.getAll();
        LOGGER.info("Retrieved all users: {}", userList);
        return userList;
    }

    @Transactional
    public User save(User user) {
        final int savedId = userRepository.save(user);

        final List<Content> allContents = contentService.getAll(null, null);

        for (Content content : allContents) {
            gameService.save(new Game.Builder()
                    .setUserId(savedId)
                    .setContentId(content.getId())
                    .setLevelId(1).build());
        }

        final User savedUser = userRepository.getById(savedId);
        LOGGER.info("Saved User: {}", savedUser);
        return savedUser;
    }

    @Transactional
    public User update(User user) {
        final int userId = user.getId();
        final int affectedRows = userRepository.update(user);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find user with id (not updated): " + userId);
        }

        final User updatedUser = userRepository.getById(userId);
        LOGGER.info("Updated User: {}", updatedUser);
        return updatedUser;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = userRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find user with id (not deleted): " + id);
        }

        LOGGER.info("Deleted user (deleted rows: {})", affectedRows);
    }
}
