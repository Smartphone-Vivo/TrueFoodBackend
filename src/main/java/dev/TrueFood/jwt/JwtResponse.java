package dev.TrueFood.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //todo че делает
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
}
