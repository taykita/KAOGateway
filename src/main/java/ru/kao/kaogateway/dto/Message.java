package ru.kao.kaogateway.dto;

import java.util.Map;

public class Message {
    public Message(Map<String,String> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    /**
     * Message body
     */
    public String body;

    /**
     * Static headers
     */
    public Map<String, String> headers;
}
