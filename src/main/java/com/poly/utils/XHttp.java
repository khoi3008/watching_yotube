package com.poly.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class XHttp {
    private String value;

    public XHttp(String value) {
        this.value = value;
    }

    public <T> T toEntity(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    public static XHttp of(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return new XHttp(sb.toString());
    }
}
