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
                "    Respuesta:  sobre el tema de " + tema+". ademas proporciona una referencia valida de la web, es decir la url sobre el tema de la pregunta generada" +
                " diciendo URL: ");
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

    public String generateContenido(String tema, String asignatura) throws HttpClientErrorException.TooManyRequests {
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
        message.put("content", "Actua como experto en resumir un tema: quiero que me des un resumen de lo mas importante sobre el \n" +
                "      tema "+tema+" relacionado a la asignatura "+asignatura+" donde haya cinco parrafos de 35 palabras cada uno de los parrafos:el primer parrafo que hable sobre un concepto general" +
                ", y los otro parrafos sobre algo importante, puede ser la importancias, entre otras cosas. el quinto parrafo quiero que hable sobre algun hecho importante o ejemplos de como usarlo. Ese ultimo parrafo tienes que ver a cual mejor se adapta en base al tema dado. cada parrafo debe contener informacion clara, especifica y consisa, no utlizar conectores");
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

    @Override
    public String generateContenidoMapa2(String tema, String asignatura) throws HttpClientErrorException.TooManyRequests {
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
        message.put("content", "hazme un resumen detallado sobre el tema "+tema+" para una asignatura de "+asignatura+"\n" +
                "                hazme 6 parrafos y cada parrafo debe tener como titulo un subtema no tan largo y\n" +
                "                cada parrafo debe estar el parrafo de 35 palabras cada uno de los parrafos con informacion especifica, sin nada de conectores pero sin \n" +
                "                mostrar un mensaje introductorio. la informacion se mostrara de la siguiente forma: " +
                "subtema1 pero sin la palabra subtema 1" +
                "descipcion del subtema1" +
                "subtema2 pero sin la palabra subtema 2" +
                "descripcion del subtema2" +
                "y asi sucesivamente" );
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

