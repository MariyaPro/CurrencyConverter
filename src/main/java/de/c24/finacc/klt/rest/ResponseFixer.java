package de.c24.finacc.klt.rest;

import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

@Data
public class ResponseFixer implements Serializable {
    private boolean success;
    private long timestamp;
    private String base;
    private Calendar date;
    private Map<String, String> rates;
}
