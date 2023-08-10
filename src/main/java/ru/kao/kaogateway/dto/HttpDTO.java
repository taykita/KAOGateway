package ru.kao.kaogateway.dto;

import java.util.Map;

public class HttpDTO {
    public HttpDTO(Map<String, String> headers, byte[] message) {
        this.message = message;
        this.headers = headers;
    }

    public HttpDTO(String path, Map<String, String> headers, byte[] message) {
        this.path = path;
        this.message = message;
        this.headers = headers;
    }

    /**
     * URL-path
     */
    public String path;

    /**
     * Message
     */
    public byte[] message;

    /**
     * Static headers
     */
    public Map<String, String> headers;
}
