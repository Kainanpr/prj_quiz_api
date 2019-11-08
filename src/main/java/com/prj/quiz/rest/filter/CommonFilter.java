package com.prj.quiz.rest.filter;

import com.prj.quiz.util.ObjectBuilder;

import java.util.Objects;

public final class CommonFilter {
    private final Integer contentId;
    private final Integer levelId;
    private final String name;

    //Note: the constructor for this class must be public so that it can be instantiated by Spring
    public CommonFilter(Integer contentId, Integer levelId, String name) {
        this.contentId = contentId;
        this.levelId = levelId;
        this.name = name;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonFilter that = (CommonFilter) o;
        return Objects.equals(contentId, that.contentId) &&
                Objects.equals(levelId, that.levelId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, levelId, name);
    }

    @Override
    public String toString() {
        return "CommonFilter{" +
                "contentId=" + contentId +
                ", levelId=" + levelId +
                ", name='" + name + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer contentId;
        private Integer levelId;
        private String name;

        public Builder setContentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        public Builder setLevelId(Integer levelId) {
            this.levelId = levelId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CommonFilter build() {
            return new CommonFilter(contentId, levelId, name);
        }
    }
}
