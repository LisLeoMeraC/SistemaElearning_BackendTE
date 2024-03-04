package com.sistema.elearning.Servicios;

import org.springframework.web.client.HttpClientErrorException;

public interface ChatGPTService {
    String generateQuestion(String tema) throws HttpClientErrorException.TooManyRequests;

    String generateContenido(String tema, String asignatura) throws HttpClientErrorException.TooManyRequests;

    String generateContenidoMapa2(String tema, String asignatura) throws  HttpClientErrorException.TooManyRequests;


}
