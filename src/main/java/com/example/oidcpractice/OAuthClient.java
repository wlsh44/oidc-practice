package com.example.oidcpractice;

import java.net.URI;

public interface OAuthClient {
    URI getAuthenticationURI();
    TokenResponse getIdToken(String code);
}
