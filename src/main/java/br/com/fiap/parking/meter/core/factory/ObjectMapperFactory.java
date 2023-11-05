package br.com.fiap.parking.meter.core.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {

    private static ObjectMapper objectMapper;

    public static ObjectMapper get() {
        if(objectMapper == null) {
            objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
            return objectMapper;
        }
        return objectMapper;
    }

}
