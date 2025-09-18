package com.soham.lnd.ai.experiments.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ChatModelCallAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    private final ChatModel chatModel;
    private final ChatClient chatClient;

    public AiConfig(ChatClient.Builder chatClientBuilder, ChatModel openAiChatModel) {
        this.chatModel = openAiChatModel;

        var advisor = ChatModelCallAdvisor.builder()
                .chatModel(openAiChatModel)
                .build();

        this.chatClient = chatClientBuilder
                .defaultAdvisors(advisor)
                .build();
    }
}
