package com.prj.quiz.rest.dto.write;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public class GameWriteDto {
    private final Integer userId;
    private final Integer contentId;
    private final Integer levelId;

    private GameWriteDto(Integer userId, Integer contentId, Integer levelId) {
        this.userId = userId;
        this.contentId = contentId;
        this.levelId = levelId;
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
        GameWriteDto that = (GameWriteDto) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(levelId, that.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentId, levelId);
    }

    @Override
    public String toString() {
        return "GameWriteDto{" +
                "userId=" + userId +
                ", contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer userId;
        private Integer contentId;
        private Integer levelId;

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
        public GameWriteDto build() {
            Assert.notNull(userId, "UserId must not be null");
            Assert.notNull(contentId, "ContentId must not be null");
            Assert.notNull(levelId, "LevelId must not be null");

            return new GameWriteDto(userId, contentId, levelId);
        }
    }
}
