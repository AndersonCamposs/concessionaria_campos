package com.example.concessionaria_campos.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String roleName;

    UserRole(String roleName) { this.roleName = roleName; }

    public String getRoleName() { return roleName; }
}
