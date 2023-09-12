package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.client.HttpClientErrorException;


import java.util.concurrent.atomic.AtomicLong;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("${OPENAI_API_KEY}")
    private String openaiApiKey;

    private final String baseUrl = "https://api.openai.com/v1/chat/completions";  // URL de la API de OpenAI

    @Override
    public String generateQuestion(String tema) throws HttpClientErrorException.TooManyRequests {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("Intentando comunicarse con OpenAI...");  // Log 1
        System.out.println("Usando la API Key: " + openaiApiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Piensa como profesor de secundaria: Hazme una pregunta con 4 opciones pero con texto no muy largos mas la respuesta que diga: \n" +
                "    Respuesta:  sobre el tema de " + tema);
        messages.add(message);
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.postForObject(baseUrl, entity, String.class);
        } catch (HttpClientErrorException.TooManyRequests e) {
            // Puedes agregar más lógica aquí, como logging, si lo deseas.
            throw e;
        }
    }
}

