package com.denlavor.cep.client;

import com.denlavor.cep.domain.dto.DistritoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// DOC de apoio: https://www.baeldung.com/rest-template#1-get-plain-json
// Projeto notflix utiliza o RestTemplate (exemplo): https://gitlab.idwall.space/components/access/notiflix-service-legacy
// Estudar tipos genéricos
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html

@Component
public class ApiClient {
    RestTemplate restTemplate = new RestTemplate();

    public List<DistritoDTO> get(String url)  {
//        ResponseEntity<DistritoDTO> response = restTemplate.getForEntity(url, DistritoDTO.class);
//
//        return response.getBody();

        ParameterizedTypeReference<List<DistritoDTO>> responseType =
                new ParameterizedTypeReference<List<DistritoDTO>>() {};

        ResponseEntity<List<DistritoDTO>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        List<DistritoDTO> distritos = responseEntity.getBody();

        return distritos;
    }
}
