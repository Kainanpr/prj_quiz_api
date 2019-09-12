package com.prj.quiz.rest.dto.write;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class StudyWriteDto {
    @NotBlank
    private final String word;
    @NotBlank
    private final String translation;
    @NotNull
    private final Integer contentId;
    @NotNull
    private final Integer levelId;

    @JsonCreator
    private StudyWriteDto(String word, String translation, Integer contentId, Integer levelId) {
        this.word = word;
        this.translation = translation;
        this.contentId = contentId;
        this.levelId = levelId;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
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
        StudyWriteDto that = (StudyWriteDto) o;
        return Objects.equals(word, that.word) &&
                Objects.equals(translation, that.translation) &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(levelId, that.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, translation, contentId, levelId);
    }

    @Override
    public String toString() {
        return "StudyWriteDto{" +
                "word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                ", contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String word;
        private String translation;
        private Integer contentId;
        private Integer levelId;

        public Builder setWord(String word) {
            this.word = word;
            return this;
        }

        public Builder setTranslation(String translation) {
            this.translation = translation;
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
        public StudyWriteDto build() {
            Assert.notNull(word, "Word must not be null");
            Assert.notNull(translation, "Translation must not be null");
            Assert.notNull(contentId, "ContentId must not be null");
            Assert.notNull(levelId, "LevelId must not be null");

            return new StudyWriteDto(word, translation, contentId, levelId);
        }
    }
}
