package de.c24.finacc.klt.rest;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;


public class FixerResponse implements Serializable {
    private boolean success;
    private long timestamp;
    private String base;
    private Calendar date;
    private Map<String,Double> rates;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
