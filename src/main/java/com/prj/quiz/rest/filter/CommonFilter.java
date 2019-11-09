package com.prj.quiz.rest.filter;

import com.prj.quiz.util.ObjectBuilder;

import java.util.Objects;

public final class CommonFilter {
    private final Integer contentId;
    private final Integer levelId;
    private final String themeName;
    private final String contentName;

    //Note: the constructor for this class must be public so that it can be instantiated by Spring
    public CommonFilter(Integer contentId, Integer levelId, String themeName, String contentName) {
        this.contentId = contentId;
        this.levelId = levelId;
        this.themeName = themeName;
        this.contentName = contentName;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public String getThemeName() { return  themeName; }

    public String getContentName() { return contentName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonFilter that = (CommonFilter) o;
        return Objects.equals(contentId, that.contentId) &&
                Objects.equals(levelId, that.levelId) &&
                Objects.equals(themeName, that.themeName)&&
                Objects.equals(contentName, that.contentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, levelId, themeName, contentName);
    }

    @Override
    public String toString() {
        return "CommonFilter{" +
                "contentId=" + contentId +
                ", levelId=" + levelId +
                ", themeName=" + themeName +
                ", contentName=" + contentName +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer contentId;
        private Integer levelId;
        private String themeName;
        private String contentName;

        public Builder setContentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder setLevelId(Integer levelId) {
            this.levelId = levelId;
            return this;
        }

        public Builder setThemeName(String themeName) {
            this.themeName = themeName;
            return this;
        }

        public Builder setContentName(String contentName) {
            this.contentName = contentName;
            return this;
        }

        @Override
        public CommonFilter build() {
            return new CommonFilter(contentId, levelId, themeName, contentName);
        }
    }
}
