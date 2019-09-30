package com.prj.quiz.rest.filter;

import com.prj.quiz.util.ObjectBuilder;

import java.util.Objects;

public final class CommonFilter {
    private final Integer contentId;
    private final Integer levelId;

    //Note: the constructor for this class must be public so that it can be instantiated by Spring
    public CommonFilter(Integer contentId, Integer levelId) {
        this.contentId = contentId;
        this.levelId = levelId;
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
        CommonFilter that = (CommonFilter) o;
        return Objects.equals(contentId, that.contentId) &&
                Objects.equals(levelId, that.levelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, levelId);
    }

    @Override
    public String toString() {
        return "CommonFilter{" +
                "contentId=" + contentId +
                ", levelId=" + levelId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer contentId;
        private Integer levelId;

        public Builder setContentId(Integer contentId) {
            this.contentId = contentId;
            return this;
        }

        @Override
        public CommonFilter build() {
            return new CommonFilter(contentId, levelId);
        }
    }
}
