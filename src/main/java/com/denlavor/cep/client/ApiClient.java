package com.denlavor.cep.client;

import com.denlavor.cep.domain.dto.district.DistritoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// DOC de apoio: https://www.baeldung.com/rest-template#1-get-plain-json
// Projeto notflix utiliza o RestTemplate (exemplo): https://gitlab.idwall.space/components/access/notiflix-service-legacy
// Estudar tipos genéricos
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html

@Slf4j
@Component
public class ApiClient {
    RestTemplate restTemplate = new RestTemplate();

    @Cacheable(value = "apiClient")
    public List<DistritoDTO> get(String url)  {
//        ResponseEntity<DistritoDTO> response = restTemplate.getForEntity(url, DistritoDTO.class);
//
//        return response.getBody();

        log.info("To no ApiClient");

        ParameterizedTypeReference<List<DistritoDTO>> responseType =
                new ParameterizedTypeReference<List<DistritoDTO>>() {};

        ResponseEntity<List<DistritoDTO>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        List<DistritoDTO> distritos = responseEntity.getBody();

        return distritos;
    }
}
