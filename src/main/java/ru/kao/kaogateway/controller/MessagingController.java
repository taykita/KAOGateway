package ru.kao.kaogateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kao.kaogateway.dto.HttpDTO;
import ru.kao.kaogateway.dto.JMSDTO;
import ru.kao.kaogateway.dto.KafkaDTO;

@RestController
public class MessagingController {

    @PostMapping("jms")
    public JMSDTO jmsSend() {
        throw new UnsupportedOperationException("This operation will be added soon");
    }

    @PostMapping("kafka")
    public KafkaDTO kafkaSend() {
        throw new UnsupportedOperationException("This operation will be added soon");
    }

    @PostMapping("http")
    public HttpDTO mqSend() {
        throw new UnsupportedOperationException("This operation will be added soon");
    }
}
