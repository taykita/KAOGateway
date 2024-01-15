package ru.kao.kaogateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.HttpMessage;
import ru.kao.kaogateway.dto.KafkaDTO;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.transport.http.HttpTransport;
import ru.kao.kaogateway.util.JSONUtil;

import static ru.kao.kaogateway.transport.http.HttpMethod.POST;

@Service
public class MessagingService {
    @Autowired
    public MessagingService(@Qualifier("transportForGateway") HttpTransport httpTransportForGateway,
                            @Qualifier("transportForServer") HttpTransport httpTransportForServer) {
        this.httpTransportForGateway = httpTransportForGateway;
        this.httpTransportForServer = httpTransportForServer;
    }

    private final HttpTransport httpTransportForGateway;
    private final HttpTransport httpTransportForServer;

//    public JSONObject kafkaSend(KafkaDTO kafkaDTO) {
//
//    }

    public JSONObject httpSend(HttpDTO httpDTO) throws TransportException {
        Message requestMessage = new HttpMessage(httpDTO.getHeaders(), httpDTO.getMessage(), httpDTO.getMethod());
        Message responseMessage = httpTransportForServer.send(requestMessage, httpDTO.getPath());
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
            Message responseMessage = httpTransportForGateway.send(requestMessage, "/http-to-server");
            return JSONUtil.messageToJSON(responseMessage);
        } catch (JSONException e) {
            throw new TransportException(e);
        }
    }
}
