package com.prj.quiz.model;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

public final class Theme {
    private final Integer id;
    private final String name;
    private final List<Content> contents;

    private Theme(Integer id, String name, List<Content> contents) {
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

    public List<Content> getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return Objects.equals(id, theme.id) &&
                Objects.equals(name, theme.name) &&
                Objects.equals(contents, theme.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private List<Content> contents;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setContents(List<Content> contents) {
            this.contents = contents;
            return this;
        }

        @Override
        public Theme build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(name, "Name must not be null");
            Assert.notNull(contents, "Contents must not be null");

            return new Theme(id, name, contents);
        }
    }
}
