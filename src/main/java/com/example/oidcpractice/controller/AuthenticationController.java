package com.example.oidcpractice.controller;

import com.example.oidcpractice.service.AuthenticationService;
import com.example.oidcpractice.controller.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/{oauthProvider}/oauth")
    public ResponseEntity<Void> redirectToAuthServer(@PathVariable String oauthProvider) {
        URI providerURI = service.getAuthProviderURI(oauthProvider);

        log.info("providerURI = {}", providerURI);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(providerURI);
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .headers(headers)
                .build();
    }

    @GetMapping("/{oauthProvider}/callback")
    public ResponseEntity<TokenResponse> callback(@PathVariable String oauthProvider, @RequestParam String code) {
        log.info("code = {}", code);
        TokenResponse tokenResponse = service.getTokenResponse(oauthProvider, code);
        log.info("tokenResponse = {}", tokenResponse);
        return ResponseEntity.ok(tokenResponse);
    }
}
