package com.example.oidcpractice.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OAuthClientFactory {

    private final Map<OAuthProvider, OAuthClient> clientMap;

    public OAuthClientFactory(List<OAuthClient> clients) {
        clientMap = new HashMap<>();
        clients.forEach(client -> clientMap.put(client.getPlatform(), client));
    }

    public OAuthClient getOAuthClient(String platform) {
        return clientMap.get(OAuthProvider.of(platform));
    }
}
