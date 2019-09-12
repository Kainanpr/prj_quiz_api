package com.prj.quiz.model;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class Study {
    private final Integer id;
    private final String word;
    private final String translation;
    private final Integer contentId;
    private final Integer levelId;

    private Study(Integer id, String word, String translation, Integer contentId, Integer levelId) {
        this.id = id;
        this.word = word;
        this.translation = translation;
        this.contentId = contentId;
        this.levelId = levelId;
    }

    public Integer getId() {
        return id;
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
        Study study = (Study) o;
        return Objects.equals(id, study.id) &&
                Objects.equals(word, study.word) &&
                Objects.equals(translation, study.translation) &&
                Objects.equals(contentId, study.contentId) &&
                Objects.equals(levelId, study.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, translation, contentId, levelId);
    }

    @Override
    public String toString() {
        return "Study{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                ", contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String word;
        private String translation;
        private Integer contentId;
        private Integer levelId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

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
        public Study build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(word, "Word must not be null");
            Assert.notNull(translation, "Translation must not be null");
            Assert.notNull(contentId, "ContentId must not be null");
            Assert.notNull(levelId, "LevelId must not be null");

            return new Study(id, word, translation, contentId, levelId);
        }
    }
}
