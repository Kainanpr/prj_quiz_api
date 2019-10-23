package com.prj.quiz.service;

import com.prj.quiz.model.Content;
import com.prj.quiz.model.Game;
import com.prj.quiz.model.User;
import com.prj.quiz.persistence.repository.ContentRepository;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentService.class);

    private final ContentRepository contentRepository;

    private final UserService userService;
    private final GameService gameService;

    public ContentService(ContentRepository contentRepository, UserService userService, GameService gameService) {
        this.contentRepository = contentRepository;
        this.userService = userService;
        this.gameService = gameService;
    }

    public Content getById(Integer id) {
        final Content content = contentRepository.getById(id);

        if (content == null) {
            throw new ObjectNotFoundException("Content not found! ID: " + id);
        }

        LOGGER.info("Retrieved content by ID: {}", content);
        return content;
    }

    public List<Content> getAll(Integer themeId) {
        final List<Content> contentList = contentRepository.getAll(themeId);
        LOGGER.info("Retrieved all contents: {}", contentList);
        return contentList;
    }

    @Transactional
    public Content save(Content content) {
        final int savedId = contentRepository.save(content);

        final List<User> allUsers = userService.getAll();

        for (User user : allUsers) {
            gameService.save(new Game.Builder()
                    .setUserId(user.getId())
                    .setContentId(savedId)
                    .setLevelId(1).build());
        }

        final Content savedContent = contentRepository.getById(savedId);
        LOGGER.info("Saved Content: {}", savedContent);
        return savedContent;
    }

    @Transactional
    public Content update(Content content) {
        final int contentId = content.getId();
        final int affectedRows = contentRepository.update(content);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find content with id (not updated): " + contentId);
        }

        final Content updatedContent = contentRepository.getById(contentId);
        LOGGER.info("Updated Content: {}", updatedContent);
        return updatedContent;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = contentRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find content with id (not deleted): " + id);
        }

        LOGGER.info("Deleted content (deleted rows: {})", affectedRows);
    }
}
