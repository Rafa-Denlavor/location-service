package com.denlavor.cep.client;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@FeignClient(name = "portal-da-transparencia")
public class ApiClientFeign {
//    @GetMapping(value="/{program}/{file-name}",  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    Response getFile(@PathVariable("program") String program, @PathVariable("file-name") String fileName);
}