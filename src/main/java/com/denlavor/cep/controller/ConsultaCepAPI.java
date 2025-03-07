package com.denlavor.cep.controller;

import com.denlavor.cep.domain.dto.CepResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("cep")
@RestController
public class ConsultaCepAPI {
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{cep}")
    public CepResponseDTO consultaCep(@PathVariable("cep") String cep) {
        ResponseEntity<CepResponseDTO> response = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), CepResponseDTO.class);

        return response.getBody();
    }
}
