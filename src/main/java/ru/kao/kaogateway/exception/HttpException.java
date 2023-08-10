package ru.kao.kaogateway.exception;

public class HttpException extends TransportException {
    public HttpException(Exception e) {
        super(e);
    }
}
