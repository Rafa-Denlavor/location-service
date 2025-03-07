package com.denlavor.cep.controller;

import com.denlavor.cep.domain.dto.EstadoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("estado")
@RestController
public class ConsultaEstadoAPI {

    @GetMapping
    public EstadoResponseDTO consultaEstado() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EstadoResponseDTO> response = restTemplate.getForEntity("https://servicodados.ibge.gov.br/api/v1/localidades/estados", EstadoResponseDTO.class);

        return response.getBody();
    }
}
