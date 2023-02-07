package com.example.oidcpractice.client;

import com.example.oidcpractice.controller.dto.TokenResponse;

import java.net.URI;

public interface OAuthClient {
    URI getAuthenticationURI();
    TokenResponse getIdToken(String code);
    OAuthProvider getPlatform();
}
