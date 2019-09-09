package com.prj.quiz.rest.dto.write;

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
    private final String senha;

    private UserWriteDto(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWriteDto user = (UserWriteDto) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(senha, user.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, senha);
    }

    @Override
    public String toString() {
        return "UserWriteDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String name;
        private String email;
        private String senha;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setSenha(String senha) {
            this.senha = senha;
            return this;
        }

        @Override
        public UserWriteDto build() {
            Assert.notNull(name, "Name must not be null");
            Assert.notNull(email, "Email must not be null");
            Assert.notNull(senha, "Senha must not be null");

            return new UserWriteDto(name, email, senha);
        }
    }
}
