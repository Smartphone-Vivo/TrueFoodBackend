package dev.TrueFood.jwt;

import dev.TrueFood.enums.Role;
import io.jsonwebtoken.Claims;

import java.util.Set;

public class JwtUtils {

    public static JwtAuthentication generate(Claims claims){
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUserId(claims.get("id", Long.class));
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {

        String role = claims.get("roles", String.class);

        return Set.of(Role.valueOf(role));

    }
}
