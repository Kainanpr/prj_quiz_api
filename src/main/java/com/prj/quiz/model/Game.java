package com.prj.quiz.model;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public class Game {
    private final Integer id;
    private final Integer userId;
    private final Integer contentId;
    private final Integer levelId;

    private Game(Integer id, Integer userId, Integer contentId, Integer levelId) {
        this.id = id;
        this.userId = userId;
        this.contentId = contentId;
        this.levelId = levelId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(userId, game.userId) &&
                Objects.equals(contentId, game.contentId) &&
                Objects.equals(levelId, game.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, contentId, levelId);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", userId=" + userId +
                ", contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private Integer userId;
        private Integer contentId;
        private Integer levelId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder setContentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder setLevelId(Integer levelId) {
            this.levelId = levelId;
            return this;
        }

        @Override
        public Game build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(levelId, "LevelId must not be null");

            return new Game(id, userId, contentId, levelId);
        }
    }
}