package com.sistema.elearning.Servicios;

import org.springframework.web.client.HttpClientErrorException;

public interface ChatGPTService {
    String generateQuestion(String tema) throws HttpClientErrorException.TooManyRequests;
}