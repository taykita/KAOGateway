package ru.kao.kaogateway.dto;

import ru.kao.kaogateway.transport.http.HttpMethod;

import java.util.Map;

public class HttpMessage extends Message {
    public HttpMessage(Map<String, String> headers, String body) {
        super(headers, body);
    }

    public HttpMessage(Map<String, String> headers,String body, HttpMethod httpMethod) {
        super(headers, body);
        this.httpMethod = httpMethod;
    }

    public HttpMethod httpMethod;
}
