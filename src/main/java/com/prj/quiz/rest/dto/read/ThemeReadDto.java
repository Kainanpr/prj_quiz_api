package com.prj.quiz.rest.dto.read;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

public final class ThemeReadDto {
    private final Integer id;
    private final String name;
    private final List<ContentReadDto> contents;

    private ThemeReadDto(Integer id, String name, List<ContentReadDto> contents) {
        this.id = id;
        this.name = name;
        this.contents = contents;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ContentReadDto> getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeReadDto that = (ThemeReadDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents);
    }

    @Override
    public String toString() {
        return "ThemeReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private List<ContentReadDto> contents;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setContents(List<ContentReadDto> contents) {
            this.contents = contents;
            return this;
        }

        @Override
        public ThemeReadDto build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(name, "Name must not be null");
            Assert.notNull(contents, "Contents must not be null");

            return new ThemeReadDto(id, name, contents);
        }
    }
}
