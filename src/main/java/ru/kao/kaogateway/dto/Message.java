package ru.kao.kaogateway.dto;

import java.util.Map;

public class Message {
    public Message(Map<String,String> headers, byte[] body) {
        this.headers = headers;
        this.body = body;
    }

    /**
     * Message body
     */
    public byte[] body;

    /**
     * Static headers
     */
    public Map<String, String> headers;
}
