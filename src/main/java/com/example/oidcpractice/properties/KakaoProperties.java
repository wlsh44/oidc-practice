package com.example.oidcpractice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoProperties {
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String redirectUri;
    private String tokenUrl;
    private List<String> scopes;
    private String authUri;
}
