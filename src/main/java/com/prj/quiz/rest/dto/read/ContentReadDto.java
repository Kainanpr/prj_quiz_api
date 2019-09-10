package com.prj.quiz.rest.dto.read;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class ContentReadDto {
    private final Integer id;
    private final String name;
    private final Integer themeId;

    private ContentReadDto(Integer id, String name, Integer themeId) {
        this.id = id;
        this.name = name;
        this.themeId = themeId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getThemeId() {
        return themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentReadDto that = (ContentReadDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(themeId, that.themeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, themeId);
    }

    @Override
    public String toString() {
        return "ContentReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", themeId=" + themeId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private Integer themeId;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setThemeId(Integer themeId) {
            this.themeId = themeId;
            return this;
        }

        @Override
        public ContentReadDto build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(name, "Name must not be null");
            Assert.notNull(themeId, "ThemeId must not be null");

            return new ContentReadDto(id, name, themeId);
        }
    }
}
