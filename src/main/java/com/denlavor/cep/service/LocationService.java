package com.denlavor.cep.service;

import com.denlavor.cep.client.ApiClient;
import com.denlavor.cep.domain.Distrito;
import com.denlavor.cep.domain.dto.district.DistritoDTO;
import com.denlavor.cep.repository.DistrictRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private DistrictRepository repository;
    private final ObjectMapper normalize = new ObjectMapper();
    private final ApiClient apiClient;
    @Value("${ibge.api.url}/distritos")
    private String allDistrictsUrl;
    @Value("${ibge.api.url}/estados/%s/distritos")
    private String districtByUfUrl;
    @Autowired
    @Qualifier("district-dlq-queue")
    private RabbitTemplate rabbitTemplate;
//
//    @RabbitListener(queues = "district-queue")
//    public void listener(String message) {
//        System.out.println(message);
//    }

    @RabbitListener(queues = "district-dlq-queue")
    public void listenerDLQ(String message) {
        System.out.println(message);
    }

    public LocationService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Object loadDistricts() throws JsonProcessingException {
        var response = apiClient.get(allDistrictsUrl);
        List<Distrito> distritoList =  new ArrayList<>();

        response.forEach(distritoDTO -> {
            Distrito distrito = Distrito.builder()
                    .id(UUID.randomUUID())
                    .name(distritoDTO.getNome())
                    // TO DO: Tentar salvar os distritos corretamente
                    .districts(distritoDTO.getMunicipio())
                    .build();

            distritoList.add(distrito);

        });

        repository.saveAll(distritoList);

        return null;

    }

//    @Cacheable(value = "districtsByUf")
    public Object loadDistrict(String ufSelected) throws JsonProcessingException {
        try {
            var response = apiClient.get(String.format(districtByUfUrl, ufSelected ));
            List<Distrito> distritoList =  new ArrayList<>();

            response.forEach(distritoDTO -> {
                Distrito distrito = Distrito.builder()
                        .id(UUID.randomUUID())
                        .name(distritoDTO.getNome())
                        .districts(distritoDTO.getMunicipio())
                        .build();

                distritoList.add(distrito);

            });

            rabbitTemplate.convertAndSend("district-queue", "Distrito carregado!");

            return distritoList;

        } catch(Error err) {
            rabbitTemplate.convertAndSend("district-dlq-queue","Erro ao carregar distrito!" + err.getMessage());
        }

        return null;
    }
}

