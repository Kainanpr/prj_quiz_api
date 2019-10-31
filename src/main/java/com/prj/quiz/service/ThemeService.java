package com.prj.quiz.service;

import com.prj.quiz.model.Theme;
import com.prj.quiz.persistence.repository.ThemeRepository;
import com.prj.quiz.rest.filter.CommonFilter;
import com.prj.quiz.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThemeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeService.class);

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Theme getById(Integer id) {
        final Theme theme = themeRepository.getById(id);

        if (theme == null) {
            throw new ObjectNotFoundException("Theme not found! ID: " + id);
        }

        LOGGER.info("Retrieved theme by ID: {}", theme);
        return theme;
    }

    public List<Theme> getAll(CommonFilter commonFilter) {
        final List<Theme> themeList = themeRepository.getAll(commonFilter);
        LOGGER.info("Retrieved all themes: {}", themeList);
        return themeList;
    }

    @Transactional
    public Theme save(Theme theme) {
        final int savedId = themeRepository.save(theme);

        final Theme savedTheme = themeRepository.getById(savedId);
        LOGGER.info("Saved Theme: {}", savedTheme);
        return savedTheme;
    }

    @Transactional
    public Theme update(Theme theme) {
        final int themeId = theme.getId();
        final int affectedRows = themeRepository.update(theme);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find theme with id (not updated): " + themeId);
        }

        final Theme updatedTheme = themeRepository.getById(themeId);
        LOGGER.info("Updated Theme: {}", updatedTheme);
        return updatedTheme;
    }

    @Transactional
    public void delete(Integer id) {
        final int affectedRows = themeRepository.delete(id);

        if (affectedRows == 0) {
            throw new ObjectNotFoundException("Could not find theme with id (not deleted): " + id);
        }

        LOGGER.info("Deleted theme (deleted rows: {})", affectedRows);
    }
}
