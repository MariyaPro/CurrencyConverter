package de.c24.finacc.klt.rest;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConverterService {
    private final RestService restService;

    public ConverterService(RestService restService) {
        this.restService = restService;
    }

    public double convert(Double amount, String from, String to) {

        Map<String, Double> mapCurrency = restService.getFixerJSON().getRates();
        return amount * mapCurrency.get(to) / mapCurrency.get(from);
    }
}
