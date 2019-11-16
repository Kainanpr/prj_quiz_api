package com.prj.quiz.rest.dto.read;

import com.prj.quiz.model.Theme;
import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class ContentReadDto {
    private final Integer id;
    private final String name;
    private final Theme theme;

    private ContentReadDto(Integer id, String name, Theme theme) {
        this.id = id;
        this.name = name;
        this.theme = theme;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Theme getTheme() {
        return theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentReadDto that = (ContentReadDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(theme, that.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, theme);
    }

    @Override
    public String toString() {
        return "ContentReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme=" + theme +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private Theme theme;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTheme(Theme theme) {
            this.theme = theme;
            return this;
        }

        @Override
        public ContentReadDto build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(name, "Name must not be null");

            return new ContentReadDto(id, name, theme);
        }
    }
}
