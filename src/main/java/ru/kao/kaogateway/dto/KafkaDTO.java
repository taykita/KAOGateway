package ru.kao.kaogateway.dto;

import java.io.Serializable;
import java.util.Map;

public class KafkaDTO implements Serializable {
    /**
     * Topic name
     */
    private String topic;

    /**
     * Kafka Message
     */
    private String message;

    /**
     * Kafka Key
     */
    private String key;

    /**
     * Kafka headers
     */
    private Map<String, String> headers;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
