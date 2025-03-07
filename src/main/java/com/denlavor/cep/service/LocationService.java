package com.denlavor.cep.service;

import com.denlavor.cep.client.ApiClient;
import com.denlavor.cep.domain.Distrito;
import com.denlavor.cep.domain.dto.DistritoDTO;
import com.denlavor.cep.domain.dto.DistritoResponseDTO;
import com.denlavor.cep.repository.DistrictRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private DistrictRepository repository;
    private final ObjectMapper normalize = new ObjectMapper();
    private final ApiClient apiClient;
    @Value("${ibge.api.url}/distritos")
    private String url;

    public LocationService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Object loadDistricts() throws JsonProcessingException {
        var response = apiClient.get(url);
        List<Distrito> distritoList =  new ArrayList<>();

        response.forEach(distritoDTO -> {
            Distrito distrito = Distrito.builder()
                    .id(UUID.randomUUID())
                    .name(distritoDTO.getNome())
                    // TO DO: Tentar salvar os distritos corretamente
//                    .districts(distritoDTO.getMunicipio())
                    .build();

            distritoList.add(distrito);

        });

        repository.saveAll(distritoList);

        return null;

    }
}

