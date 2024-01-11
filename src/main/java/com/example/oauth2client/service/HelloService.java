package com.example.oauth2client.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HelloService {

    private final RestClient restClient;
    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    public HelloService(OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager) {
        this.oAuth2AuthorizedClientManager = oAuth2AuthorizedClientManager;
        this.restClient = RestClient.create();
    }


    public String getData(){
        var request = OAuth2AuthorizeRequest.withClientRegistrationId("1")
                .principal("client-id")
                .build();

        var client = oAuth2AuthorizedClientManager.authorize(request);

        var tokenValue = client.getAccessToken().getTokenValue();


        var result= restClient.get().uri("http://localhost:8090/home")
                .header("Authorization","Bearer "+tokenValue)
                .retrieve().body(String.class);

        return result;
    }
}


