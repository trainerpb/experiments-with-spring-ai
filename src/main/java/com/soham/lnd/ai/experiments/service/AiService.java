package com.soham.lnd.ai.experiments.service;


import com.soham.lnd.ai.experiments.model.Batter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AiService {


    private ChatClient chatClient;


    ChatModel chatModel;

    public AiService(ChatModel chatModel) {
        this.chatModel = chatModel;
        chatClient = ChatClient.create(chatModel);
        System.out.println("AiService.AiService " + chatClient);
        System.err.println("AiService.AiService " + chatModel.getClass().getName() + " " + chatClient.getClass().getName());
        System.err.println("AiService.AiService " + chatModel.getDefaultOptions().toString());
    }

    public Batter getBestBatsman() {
//        var response=chatClient.prompt()
//                .options(OpenAiChatOptions.builder()
//
//                        .build())
//                .system("You are a math teacher").user("tell me first 10 prime numbers")
//                .call();
//        String strContent = response.content();
        var batter = chatClient.prompt().user("Who is the best batsman in the world? Use JSON with name, runs and country only.")
                .call().entity(Batter.class);

        ;
//        ChatResponse chatResponse = response.chatResponse();
//        System.out.println("AiService.getBestBatsman "+strContent);
//        System.out.println("AiService.getBestBatsman "+chatResponse);
        return batter;
    }

    public List<Batter> getBestBatsmen() {

        var batters = chatClient.prompt().user("List 10 best batsman in the world? Use JSON with name, runs and country only.")
                .call().entity(new ParameterizedTypeReference<List<Batter>>() {
                });


        return batters;
    }


    public String experiment_Output( String prompt) {

        var response = chatClient.prompt(prompt).call().chatResponse();
        var response2 =chatClient.prompt(prompt).call().chatClientResponse();
        return null;
    }

    /**
     *
     * @param problem
     * @param language
     * @return A code snippet to solve the problem in the specified language
     *
     * Observations:
     *    1. Works and defaults to Python if no language is specified
     *    2. We need to specify the language in the prompt to get code in that language
     *
     */

    public String experiment_PromptTemplateWithCodingExample( String problem,String language) {
        String prompt = "Write a program to solve this problem: "+problem;
        var response = chatClient.prompt()
                .user(prompt)
//                .system("You are a "+language+" programmer")
                .system(u-> u.text("You are a {ln} programmer").param("ln",language))
                .call().chatResponse();


        return response.getResult().getOutput().getText();
    }
}
