package com.prj.quiz.rest.filter;

import com.prj.quiz.util.ObjectBuilder;

import java.util.Objects;

public final class Login {
    private final String email;
    private final String password;

    //Note: the constructor for this class must be public so that it can be instantiated by Spring
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
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
        Login login = (Login) o;
        return Objects.equals(email, login.email) &&
                Objects.equals(password, login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private String email;
        private String password;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public Login build() {
            return new Login(email, password);
        }
    }
}
