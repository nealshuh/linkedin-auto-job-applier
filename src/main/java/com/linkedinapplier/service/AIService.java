package com.jobautomation.service;

import com.jobautomation.client.ClaudeClient;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIService {
    
    private final OpenAiService openAiService;
    private final ClaudeClient claudeClient;
    
    @Value("${api.openai.model}")
    private String gptModel;
    
    public String generateGPTResponse(String prompt) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
            .model(gptModel)
            .messages(List.of(new ChatMessage("user", prompt)))
            .build();
            
        return openAiService.createChatCompletion(request)
            .getChoices().get(0).getMessage().getContent();
    }
    
    public String generateClaudeResponse(String prompt) {
        return claudeClient.generateResponse(prompt);
    }
    
    public String analyzeForm(String formContent) {
        // Example prompt for form analysis
        String prompt = String.format("""
            Analyze this application form and provide structured JSON response:
            %s
            Return only valid JSON with no additional text.
            """, formContent);
            
        return generateGPTResponse(prompt);
    }
    
    public String generateComplexResponse(String question) {
        // Use Claude for more complex, nuanced responses
        String prompt = String.format("""
            Provide a detailed, professional response to this job application question:
            %s
            Focus on specific examples and impact. Keep the tone professional.
            """, question);
            
        return generateClaudeResponse(prompt);
    }
}