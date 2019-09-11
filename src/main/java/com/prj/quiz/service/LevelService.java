package com.prj.quiz.service;

import com.prj.quiz.model.Level;
import com.prj.quiz.persistence.repository.LevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelService.class);

    private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public List<Level> getAll() {
        final List<Level> levelList = levelRepository.getAll();
        LOGGER.info("Retrieved all levels: {}", levelList);
        return levelList;
    }
}
