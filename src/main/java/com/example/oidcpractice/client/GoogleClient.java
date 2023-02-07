package com.example.oidcpractice.client;

import com.example.oidcpractice.properties.GoogleProperties;
import com.example.oidcpractice.controller.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class GoogleClient implements OAuthClient {

    private static final String SCOPE_DELIMITER = " ";

    private final GoogleProperties properties;

    @Override
    public URI getAuthenticationURI() {
        MultiValueMap<String, String> parameters = getAuthUriParameters();
        return UriComponentsBuilder.fromUriString(properties.getAuthUri()).queryParams(parameters).build().toUri();
    }

    private MultiValueMap<String, String> getAuthUriParameters() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", properties.getClientId());
        parameters.add("scope", String.join(SCOPE_DELIMITER, properties.getScopes()));
        parameters.add("response_type", "code");
        parameters.add("redirect_uri", properties.getRedirectUri());

        return parameters;
    }

    @Override
    public TokenResponse getIdToken(String code) {
        WebClient webClient = WebClient.create(properties.getTokenUrl());

        MultiValueMap<String, String> formData = getTokenUriFormData(code);

        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();
    }


    private MultiValueMap<String, String> getTokenUriFormData(String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", properties.getClientId());
        formData.add("client_secret", properties.getClientSecret());
        formData.add("grant_type", properties.getGrantType());
        formData.add("redirect_uri", properties.getRedirectUri());
        return formData;
    }

    @Override
    public OAuthProvider getPlatform() {
        return OAuthProvider.GOOGLE;
    }
}
