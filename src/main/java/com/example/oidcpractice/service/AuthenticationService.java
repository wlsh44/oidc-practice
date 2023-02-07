package com.example.oidcpractice.service;

import com.example.oidcpractice.controller.dto.TokenResponse;
import com.example.oidcpractice.client.OAuthClient;
import com.example.oidcpractice.client.OAuthClientFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final OAuthClientFactory clientFactory;

    public URI getAuthProviderURI(String provider) {
        OAuthClient oAuthClient = getOAuthClient(provider);

        return oAuthClient.getAuthenticationURI();
    }

    private OAuthClient getOAuthClient(String provider) {
        return clientFactory.getOAuthClient(provider);
    }

    public TokenResponse getTokenResponse(String provider, String code) {
        OAuthClient oAuthClient = getOAuthClient(provider);
        return oAuthClient.getIdToken(code);
    }
}
