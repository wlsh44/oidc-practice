package com.example.oidcpractice.client;

import java.util.Arrays;

public enum OAuthProvider {
    GOOGLE("google"),
    KAKAO("kakao");

    private final String provider;

    OAuthProvider(String provider) {
        this.provider = provider;
    }

    public static OAuthProvider of(String platform) {
        return Arrays.stream(OAuthProvider.values())
                .filter(oAuthProvider -> oAuthProvider.provider.equals(platform))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 Provider가 존재하지 않습니다."));
    }
}
