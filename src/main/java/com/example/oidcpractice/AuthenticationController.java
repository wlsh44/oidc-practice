package com.example.oidcpractice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/oauth")
    public ResponseEntity<Void> redirectToAuthServer() {
        URI authRedirectionURI = service.getAuthRedirectionURI();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(authRedirectionURI);
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .headers(headers)
                .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenResponse> callback(@RequestParam String code) {
        log.info("code = {}", code);
        TokenResponse tokenResponse = service.getTokenResponse(code);
        log.info("tokenResponse = {}", tokenResponse);
        return ResponseEntity.ok(tokenResponse);
    }
}
