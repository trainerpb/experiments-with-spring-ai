package com.soham.lnd.ai.experiments.controller;

import com.soham.lnd.ai.experiments.model.Batter;
import com.soham.lnd.ai.experiments.model.Student;
import com.soham.lnd.ai.experiments.service.AiService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
}
