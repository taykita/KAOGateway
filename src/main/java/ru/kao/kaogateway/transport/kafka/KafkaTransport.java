package ru.kao.kaogateway.transport.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.TransportException;
import ru.kao.kaogateway.transport.Transport;

import java.util.List;

public class KafkaTransport implements Transport {
    private List<Producer<String, String>> producers;

    private List<Consumer<String, String>> consumers;


    @Override
    public Object send(Message msg, String destination) throws TransportException {
        return null;
    }

    @Override
    public void subscribe(Runnable callback, String destination) {

    }
}
