package dev.TrueFood.jwt;

import dev.TrueFood.entity.users.BaseUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j //todo почитать че это
@Component //todo почитать че это
public class JwtProvider {

    private final SecretKey jwtAccessSecret;

    public JwtProvider(@Value("${jwt.secret.access}") String jwtAccessSecret)
    {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
    }


 public String generateAccessToken(@NonNull BaseUser baseUser) {
     final LocalDateTime now = LocalDateTime.now();
     final Instant accessExpirationInstant = now.plusMinutes(60).atZone(ZoneId.systemDefault()).toInstant();
     final Date accessExpiration = Date.from(accessExpirationInstant);
     return Jwts.builder()
             .setSubject(baseUser.getEmail())
             .setExpiration(accessExpiration)
             .signWith(jwtAccessSecret)
             .claim("roles", baseUser.getRole() != null ? baseUser.getRole().name() : "USER")
             .claim("id", baseUser.getId())
             .compact();
 }

 public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
 }

 private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
 }

 public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
 }

 private Claims getClaims(String token, Key secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
 }

}