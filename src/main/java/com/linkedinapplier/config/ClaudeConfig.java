package com.jobautomation.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

import java.util.List;

@Component
public class ClaudeClient {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String model;
    private final Integer maxTokens;
    private final Double temperature;
    private static final String API_URL = "https://api.anthropic.com/v1/messages";

    public ClaudeClient(
            @Value("${api.claude.key}") String apiKey,
            @Value("${api.claude.model}") String model,
            @Value("${api.claude.max-tokens}") Integer maxTokens,
            @Value("${api.claude.temperature}") Double temperature) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
    }

    public String generateResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", apiKey);
        headers.set("anthropic-version", "2023-06-01");

        ClaudeRequest request = new ClaudeRequest(
            model,
            List.of(new Message("user", prompt)),
            maxTokens,
            temperature
        );

        HttpEntity<ClaudeRequest> entity = new HttpEntity<>(request, headers);
        
        ClaudeResponse response = restTemplate.postForObject(
            API_URL,
            entity,
            ClaudeResponse.class
        );

        return response != null && !response.getContent().isEmpty() 
            ? response.getContent().get(0).getText() 
            : "";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ClaudeRequest {
        private String model;
        private List<Message> messages;
        @JsonProperty("max_tokens")
        private Integer maxTokens;
        private Double temperature;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Message {
        private String role;
        private String content;
    }

    @Data
    @NoArgsConstructor
    private static class ClaudeResponse {
        private String id;
        private String model;
        private List<ContentItem> content;
    }

    @Data
    @NoArgsConstructor
    private static class ContentItem {
        private String text;
        private String type;
    }
}