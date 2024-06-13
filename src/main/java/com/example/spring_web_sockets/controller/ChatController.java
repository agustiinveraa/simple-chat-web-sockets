package com.example.spring_web_sockets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/")
    public String mostrarChat() {
        return "chat";
    }
}
