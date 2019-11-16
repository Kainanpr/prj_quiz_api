package com.prj.quiz.rest.dto.read;

import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class UserReadDto {
    private final Integer id;
    private final String name;
    private final String email;

    private UserReadDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReadDto that = (UserReadDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "UserReadDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String name;
        private String email;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public UserReadDto build() {
            if (id != null) {
                Assert.isTrue(id > 0, "ID must be greater than 0");
            }

            Assert.notNull(name, "Name must not be null");
            Assert.notNull(email, "Email must not be null");

            return new UserReadDto(id, name, email);
        }
    }
}
