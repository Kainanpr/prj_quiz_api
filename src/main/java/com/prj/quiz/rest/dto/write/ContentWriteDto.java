package com.prj.quiz.rest.dto.write;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public final class ContentWriteDto {
    @NotBlank
    private final String name;
    @NotNull
    private final Integer themeId;

    @JsonCreator
    private ContentWriteDto(String name, Integer themeId) {
        this.name = name;
        this.themeId = themeId;
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
        ContentWriteDto that = (ContentWriteDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(themeId, that.themeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, themeId);
    }

    @Override
    public String toString() {
        return "ContentWriteDto{" +
                "name='" + name + '\'' +
                ", themeId=" + themeId +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String name;
        private Integer themeId;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setThemeId(Integer themeId) {
            this.themeId = themeId;
            return this;
        }

        @Override
        public ContentWriteDto build() {
            Assert.notNull(name, "Name must not be null");
            Assert.notNull(themeId, "ThemeId must not be null");

            return new ContentWriteDto(name, themeId);
        }
    }
}
