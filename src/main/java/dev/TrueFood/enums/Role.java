package dev.TrueFood.enums;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public enum Role implements Serializable { //todo serializable хз
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

//    @Override
//    public String getAuthority() {
//        return value;
//    }
}
