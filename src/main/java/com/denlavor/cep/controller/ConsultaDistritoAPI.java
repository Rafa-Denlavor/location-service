package com.denlavor.cep.controller;

import com.denlavor.cep.domain.dto.DistritoResponseDTO;
import com.denlavor.cep.repository.DistrictRepository;
import com.denlavor.cep.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("district")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class ConsultaDistritoAPI {
    @Autowired
    LocationService locationService;
    @Autowired
    private DistrictRepository repository;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public Object consultaDistritos() throws JsonProcessingException {
        locationService.loadDistricts();
        //        ResponseEntity<DistritoResponseDTO> response = restTemplate.getForEntity("https://servicodados.ibge.gov.br/api/v1/localidades/distritos?orderBy=nome", DistritoResponseDTO.class);
//
//        return response.getBody();
        return null;
    }

    @GetMapping("{uf}")
    public Object consultaDistritoPorUf(@PathVariable("uf") String uf) {
        ResponseEntity<DistritoResponseDTO> response = restTemplate.getForEntity(String.format("https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/distritos?orderBy=nome", uf), DistritoResponseDTO.class);

        return response.getBody();
    }
}
