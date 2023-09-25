package ru.kao.kaogateway.dto;

import java.util.Map;

public class Message {
    public Message(Map<String,String> headers, String body) {
        this.headers = headers;
        this.body = body;
        UUID = headers.get("UUID");
    }

    /**
     * Message body
     */
    public String body;

    /**
     * Static headers
     */
    public Map<String, String> headers;

    /**
     * UUID
     */
    public String UUID;

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", headers=" + headers +
                ", UUID='" + UUID + '\'' +
                '}';
    }
}
