package com.Board.Board.Domain.Entity;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;
    MemberRole(String value){
        this.value = value;
    }
}
