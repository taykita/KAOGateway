package ru.kao.kaogateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.JMSDTO;
import ru.kao.kaogateway.dto.KafkaDTO;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.service.MessagingService;

@RestController
public class MessagingController {
    @Autowired
    public MessagingController(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

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
     * @return httpDTO <br> <br>
     * @throws TransportException if problem with sending http message

     */
    @PostMapping("http")
    public HttpDTO httpSend(@RequestBody HttpDTO httpDTO) throws TransportException {
        return messagingService.httpSend(httpDTO);
    }
}
