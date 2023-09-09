package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chatgpt")
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/generate-question")
    public ResponseEntity<?> generateQuestion(@RequestBody Map<String, String> requestBody) {
        String tema = requestBody.get("tema");
        String response = chatGPTService.generateQuestion(tema);
        return ResponseEntity.ok(response);
    }
}