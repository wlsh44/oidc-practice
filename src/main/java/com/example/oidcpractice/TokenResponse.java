package com.example.oidcpractice;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenResponse {
    private String idToken;
    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    private String tokenType;
    private String scope;
}
