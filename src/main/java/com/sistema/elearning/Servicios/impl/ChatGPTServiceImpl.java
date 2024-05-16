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
        body.put("model", "gpt-4-turbo");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Actua como experto en crear preguntas de opcion multiple relacionado al tema de "+tema+". Quiero una pregunta que tenga el siguiente formato:\n" +
                "Pregunta: contenido de la pregunta\n" +
                "a) contenido de la opcion 1\n" +
                "b) contenido de la opcion 2\n" +
                "c) contenido de la opcion 3\n" +
                "d) contenido de la opcion 4\n" +
                "Respuesta:  letra del literal) contenido de la respuesta\n" +
                "Argumentacion:  una breve argumentación sobre porque es la resspuesta de la respuesta. en el caso de un ejercicio, resuelvelo lo corto posible sin salto del lineas\n" +
                "URL: url  de wikipedia sobre la respuesta \n" +
                "-quiero que la respuesta se muestre alternando los literales, es decir que la respuesta debe aparecer ya sea en en la b, c o d, pero no quiero que seimpre muestre la respuesta en la a - y que no se repita la respuesta en la siguiente pregunta cuando se el usuario haga la peitcion.");
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
        body.put("model", "gpt-4-turbo");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Actua como experto en resumir un tema: quiero que me des un resumen de lo mas importante sobre el \n" +
                "      tema "+tema+" relacionado a la asignatura "+asignatura+" donde haya cinco parrafos de 25 palabras cada uno de los parrafos:el primer parrafo que hable sobre un concepto general" +
                ", y los otro parrafos sobre algo importante, puede ser la importancias, entre otras cosas. el quinto parrafo quiero que hable sobre algun hecho importante o ejemplos de como usarlo. Ese ultimo parrafo tienes que ver a cual mejor se adapta en base al tema dado." +
                "cada parrafo debe contener informacion clara, especifica y consisa, no utlizar conectores, solo mostrar las paralabras relavantes sobre el subtema maximo 25 palabras por parrafo");
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
        body.put("model", "gpt-4-turbo");
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "hazme un resumen con palabras especificas sin nada de conectores sobre el tema"+tema+" relacionado a la asignatura de"+asignatura+" siguiendo al pie de la letra las siguientes condicciones:\n" +
                "- No mostrar parrafo introductorio\n" +
                "- 6 parrafos y cada parrafo debe tener como titulo un subtema de tres palabras.\n" +
                "- Cada parrafo debe contener 20 palabras exactamente.\n" +
                "- Cada parrafo debe contener texto especifico, no usar conectores, ni texto introductorio, directo al contexto.\n" +
                "A continuación, te muestro el formato de como quiero que se muestre la respuesta:\n" +
                "nombre del subtema 1 (es sustituido por el subtema. No mostrar * en el subtema. maximo 3 palabras por cada subtema)\n" +
                "descripción del parrafo 1 (es sustituido por el contenido generado. cada parrafo debe contener 25 palabras)\n" +
                "nombre del subtema 2\n" +
                "descripcion del parrafo 2" );
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

