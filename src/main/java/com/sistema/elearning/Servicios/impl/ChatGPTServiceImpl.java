package com.sistema.elearning.Servicios.impl;

import com.sistema.elearning.Servicios.ChatGPTService;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class ChatGPTServiceImpl implements ChatGPTService {

    @Value("${openai.apikey}")
    private String apiKey;

    @Value("${openai.url:https://api.openai.com/v1/chat/completions}")
    private String OPENAI_URL;

    @Value("${openai.maxRetries:3}")
    private int MAX_RETRIES;

    @Value("${openai.waitTimeInMs:10000}")
    private long WAIT_TIME_IN_MS;  // 10 segundos

    // Token tracking
    private AtomicLong tokensUsed = new AtomicLong();

    @Override
    public String generateQuestion(String tema) throws HttpClientErrorException.TooManyRequests {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("max_tokens", 500);  // Limitar la respuesta a 500 tokens (ajustar según tus necesidades)

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", "Generar una pregunta de opción múltiple sobre el tema: " + tema));
        body.put("messages", messages);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                if (tokensUsed.get() >= 40000) { // Si ya hemos usado 40,000 tokens en este minuto
                    try {
                        Thread.sleep(60000 - WAIT_TIME_IN_MS);  // Espera el tiempo restante en este minuto
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    tokensUsed.set(0);  // Reiniciar conteo de tokens
                }

                // Llamada al endpoint de OpenAI
                ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_URL, entity, Map.class);

                // Extract tokens used from the response and add to tokensUsed
                if (response.getBody() != null && response.getBody().containsKey("usage")) {
                    Map<String, Object> usage = (Map<String, Object>) response.getBody().get("usage");
                    if (usage.containsKey("total_tokens")) {
                        tokensUsed.addAndGet((Integer) usage.get("total_tokens"));
                    }
                }

                // Return the generated message
                List<Map<String, Map<String, String>>> responseMessages = (List<Map<String, Map<String, String>>>) response.getBody().get("choices");
                if (!responseMessages.isEmpty()) {
                    return responseMessages.get(0).get("message").get("content");
                }
            } catch (HttpClientErrorException.TooManyRequests e) {
                if (i == MAX_RETRIES - 1) {
                    throw e; // Lanzar excepción si alcanzamos el número máximo de reintentos
                }
                try {
                    Thread.sleep(WAIT_TIME_IN_MS); // Espera por un tiempo determinado antes del siguiente reintento
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS, "Exceeded max retries");
    }
}
