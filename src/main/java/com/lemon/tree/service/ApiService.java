package com.lemon.tree.service;


import com.lemon.tree.util.Tracer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ApiService {
    private final static String API_POSTMAN_ECHO = "http://postman-echo.com/get";

    public ResponseEntity<String> callAnotherApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> results = restTemplate.exchange(API_POSTMAN_ECHO, HttpMethod.GET, entity, String.class);

        Tracer.info("Called another API\nREQUEST_URI: {0}\nSTATUS: {1}\nBODY: {2}\n",
                API_POSTMAN_ECHO,
                results.getStatusCode(),
                results.getBody());
        return results;
    }
}
