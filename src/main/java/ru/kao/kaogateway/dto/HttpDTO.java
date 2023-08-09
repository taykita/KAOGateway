package ru.kao.kaogateway.dto;

import java.util.Map;

public class HttpDTO {
    /**
     * URL-path
     */
    public String path;

    /**
     * Message
     */
    public Object message;

    /**
     * Static headers
     */
    public Map<String, String> headers;
}
