package com.prj.quiz.rest.dto.write;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.prj.quiz.util.ObjectBuilder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public final class UserWriteDto {
    @NotBlank
    private final String name;
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

    @JsonCreator
    private UserWriteDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWriteDto user = (UserWriteDto) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return "UserWriteDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String name;
        private String email;
        private String password;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public UserWriteDto build() {
            Assert.notNull(name, "Name must not be null");
            Assert.notNull(email, "Email must not be null");
            Assert.notNull(password, "Password must not be null");

            return new UserWriteDto(name, email, password);
        }
    }
}
