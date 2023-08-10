package ru.kao.kaogateway.transport;

import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.HttpException;
import ru.kao.kaogateway.exception.TransportException;

public interface Transport {
    Object send(Message msg, String destination) throws TransportException;
}
