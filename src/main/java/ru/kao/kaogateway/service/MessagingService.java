package ru.kao.kaogateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.HttpMessage;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.transport.http.HttpTransport;
import ru.kao.kaogateway.util.JSONUtil;

import static ru.kao.kaogateway.transport.http.HttpMethod.POST;

@Service
public class MessagingService {
    @Autowired
    public MessagingService(@Qualifier("transportForGateway") HttpTransport transportForGateway,
                            @Qualifier("transportForServer") HttpTransport transportForServer) {
        this.transportForGateway = transportForGateway;
        this.transportForServer = transportForServer;
    }

    private final HttpTransport transportForGateway;
    private final HttpTransport transportForServer;

    public JSONObject httpSend(HttpDTO httpDTO) throws TransportException {
        Message requestMessage = new HttpMessage(httpDTO.getHeaders(), httpDTO.getMessage(), httpDTO.getMethod());
        Message responseMessage = transportForServer.send(requestMessage, httpDTO.getPath());
        try {
            return JSONUtil.messageToJSON(responseMessage);
        } catch (JSONException e) {
            throw new TransportException(e);
        }
    }

    public JSONObject httpSendToGateway(String jsonDTO) throws TransportException {
        try {
            Message message = JSONUtil.JSONToMessage(new JSONObject(jsonDTO));
            Message requestMessage = new HttpMessage(message.headers, jsonDTO, POST);
            Message responseMessage = transportForGateway.send(requestMessage, "/http-to-server");
            return JSONUtil.messageToJSON(responseMessage);
        } catch (JSONException e) {
            throw new TransportException(e);
        }
    }
}
