package com.sistema.elearning.controladores;

import com.sistema.elearning.Servicios.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/api/chatgpt")
@CrossOrigin("*")
public class ChatGPTController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping("/generate-question")
    public ResponseEntity<?> generateQuestion(@RequestBody Map<String, String> requestBody) {
        try {
            String tema = requestBody.get("tema");
            String response = chatGPTService.generateQuestion(tema);
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException.TooManyRequests e) {
            return ResponseEntity.status(429).body("Too many requests to OpenAI API");
        } catch (Exception e) {
            // Aquí puedes manejar otros errores inesperados si lo consideras necesario.
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
