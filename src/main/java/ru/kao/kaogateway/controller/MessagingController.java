package ru.kao.kaogateway.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.JMSDTO;
import ru.kao.kaogateway.dto.KafkaDTO;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.service.MessagingService;
import ru.kao.kaogateway.util.HeadersUtil;
import ru.kao.kaogateway.util.LoggerUtil;

@RestController
public class MessagingController {
    @Autowired
    public MessagingController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    private static Logger logger = LoggerUtil.getLogger(MessagingController.class);

    private final MessagingService messagingService;

    @PostMapping("jms")
    public JMSDTO jmsSend(@RequestBody JMSDTO jmsDTO) {
        throw new UnsupportedOperationException("This operation will be added soon");
    }

    @PostMapping("kafka")
    public KafkaDTO kafkaSend(@RequestBody KafkaDTO kafkaDTO) {
        throw new UnsupportedOperationException("This operation will be added soon");
    }

    /**
     * Send http message
     * @param httpDTO DTO <br> <br>
     * DTO example:
     *   <pre>{@code
     *      {
     *          "path" : "/foo/bar", (HTTP path)
     *          "message" : "Hello, World!", (Request Body) (JSON)
     *          "method" : "POST" (GET, POST, DELETE, PUT, PATCH)
     *          "headers" : {
     *              "header1" : "value1",
     *              "header2" : "value2",
     *          }
     *      }
     *   }</pre>
     * @return JSON
     * @throws TransportException if problem with sending http message

     */
    @PostMapping(value = "http-to-server", produces = MediaType.APPLICATION_JSON_VALUE)
    public String httpSend(@RequestBody HttpDTO httpDTO) throws TransportException {
        logger.debug("{}: Received request for sending http message.\nMethod - {}. Headers - {}. Message - {}.", httpDTO.getHeaders().get("UUID"),
                httpDTO.getMethod(), httpDTO.getHeaders(), httpDTO.getMessage());
        return messagingService.httpSend(httpDTO).toString();
    }

    @PostMapping(value = "http", produces = MediaType.APPLICATION_JSON_VALUE)
    public String httpSendToGateway(@RequestBody String jsonDTO) throws TransportException {
        logger.debug("Received request for sending http message to gateway.\nMessage - {}.", jsonDTO);
        return messagingService.httpSendToGateway(jsonDTO).toString();
    }
}
