package ru.kao.kaogateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.HttpMessage;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.transport.http.HttpTransport;
import ru.kao.kaogateway.util.JSONUtil;

@Service
public class MessagingService {
    @Autowired
    public MessagingService(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    private final HttpTransport httpTransport;

    public JSONObject httpSend(HttpDTO httpDTO) throws TransportException {
        Message requestMessage = new HttpMessage(httpDTO.headers, httpDTO.message, httpDTO.method);
        Message responseMessage = httpTransport.send(requestMessage, httpDTO.path);
        try {
            return JSONUtil.messageToJSON(responseMessage);
        } catch (JSONException e) {
            throw new TransportException(e);
        }
//        return new HttpDTO(responseMessage.headers, responseMessage.body);
    }
}
