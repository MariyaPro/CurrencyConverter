package de.c24.finacc.klt.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Rest Service
 */
@RestController
@RequestMapping("/api")
public class RestService {

    /**
     * Test rest endpoint
     *
     * @return Map of key/values
     */
    @GetMapping("/testit")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("key", "value");
        return response;
    }

    private final RestTemplate restTemplate;

    @Value("${fixer}" + "${fixer.key}")
    private String url;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Map<String, String> getCurrenciesMap() {
        ResponseEntity<ResponseFixer> responseFull = this.restTemplate.getForEntity(url, ResponseFixer.class);
        ResponseFixer responseFixer = responseFull.getBody();

        return responseFixer == null ? Collections.EMPTY_MAP : responseFixer.getRates();
    }

    public Map<String, String> update(String currency) {
        String ulrParam = url + "&symbols=" + currency;
        ResponseEntity<ResponseFixer> responseOne = this.restTemplate.getForEntity(ulrParam, ResponseFixer.class);
        ResponseFixer responseFixer = responseOne.getBody();

        return responseFixer == null ? Collections.EMPTY_MAP : responseFixer.getRates();
    }
}
