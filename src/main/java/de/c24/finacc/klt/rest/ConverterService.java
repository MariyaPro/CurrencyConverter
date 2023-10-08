package de.c24.finacc.klt.rest;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class ConverterService {
    private final RestService restService;
    private Map<String, String> currenciesMap;

    public ConverterService(RestService restService) {
        this.restService = restService;
        currenciesMap = restService.getCurrenciesMap();
    }

    public double convert(Double amount, String from, String to) {
        currenciesMap.put(from, restService.update(from).get(from));
        currenciesMap.put(to, restService.update(to).get(to));
        double valFrom = Double.parseDouble(currenciesMap.get(from));
        double valTo = Double.parseDouble(currenciesMap.get(to));
        return amount * valTo / valFrom;
    }

    public Set<String> getCurrenciesNames() {
        return currenciesMap.keySet();
    }
}
