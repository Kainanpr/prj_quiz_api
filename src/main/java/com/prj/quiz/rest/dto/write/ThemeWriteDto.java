package com.prj.quiz.rest.dto.write;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public final class ThemeWriteDto {
    @NotBlank
    private final String name;

    @JsonCreator
    private ThemeWriteDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeWriteDto that = (ThemeWriteDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ThemeWriteDto{" +
                "name='" + name + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public ThemeWriteDto build() {
            Assert.notNull(name, "Name must not be null");

            return new ThemeWriteDto(name);
        }
    }
}
