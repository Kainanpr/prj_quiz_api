package com.prj.quiz.model;

public enum RolesEnum {
    ADMIN("ROLES_ADMIN"),
    USER("ROLES_USER");

    private final String description;

    RolesEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
