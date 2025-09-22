package com.soham.lnd.ai.experiments.controller;

import com.soham.lnd.ai.experiments.model.Batter;
import com.soham.lnd.ai.experiments.service.AiService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/experiment")
@CrossOrigin("*")

public class ExperimentController {

    private final AiService chatService;

    public ExperimentController(AiService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(value = "/ask")
    public Batter whoIsBestBatsman() throws IOException {
        return chatService.getBestBatsman();
    }

    @GetMapping(value = "/ask_list")
    public List<Batter> whoAreBestBatsmen() throws IOException {
        return chatService.getBestBatsmen();
    }

    @GetMapping("/output")
    public  String experiment_Output(@RequestParam("q") String pText){
        return chatService.experiment_Output(pText);
    }

    @GetMapping("/code")
    public  String experiment_PromptTemplateWithCodingExample(@RequestParam("problem") String probelmText, @RequestParam(value="lang", required = false) String lang){
        return chatService.experiment_PromptTemplateWithCodingExample(probelmText, lang);
    }
}
