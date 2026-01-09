package dev.TrueFood.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority { //todo serializable хз
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}
