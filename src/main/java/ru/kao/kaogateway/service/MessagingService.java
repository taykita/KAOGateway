package ru.kao.kaogateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.transport.http.HttpTransport;

@Service
public class MessagingService {
    @Autowired
    public MessagingService(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    private final HttpTransport httpTransport;

    public void httpSend(HttpDTO httpDTO) {

    }
}
