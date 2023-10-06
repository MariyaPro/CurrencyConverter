package de.c24.finacc.klt.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private FixerResponse fixerResponse;

    @Value("${fixer}" + "${fixer.key}")
    private String url;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public FixerResponse getFixerJSON() {
        if (fixerResponse == null || System.currentTimeMillis() / 1000 - fixerResponse.getTimestamp() > 300) {
            ResponseEntity<FixerResponse> response = this.restTemplate.getForEntity(url, FixerResponse.class);
            fixerResponse = response.getBody();
        }
        //  System.out.println(fixerResponse.getTimestamp());
        //  System.out.println(System.currentTimeMillis() / 1000);
        //  System.out.println("----");
        return fixerResponse;
    }


}
