package com.example.oidcpractice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final OAuthClient googleClient;

    public URI getAuthRedirectionURI() {
        return googleClient.getAuthenticationURI();
    }

    public TokenResponse getTokenResponse(String code) {
        return googleClient.getIdToken(code);
    }
}
