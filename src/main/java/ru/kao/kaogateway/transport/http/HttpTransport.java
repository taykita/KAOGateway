package ru.kao.kaogateway.transport.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import ru.kao.kaogateway.dto.HttpMessage;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.HttpException;
import ru.kao.kaogateway.transport.Transport;
import ru.kao.kaogateway.util.HeadersUtil;
import ru.kao.kaogateway.util.LoggerUtil;

import java.io.IOException;
import java.util.Arrays;

public class HttpTransport implements Transport {
    private final Logger logger = LoggerUtil.getLogger(HttpTransport.class);

    public HttpTransport(String serverUrl, HttpClient httpClient) {
        this.SERVER_URL = serverUrl;
        this.httpClient = httpClient;
    }

    private final String SERVER_URL;
    private final HttpClient httpClient;

    @Override
    public Message send(Message message, String destination) throws HttpException {
        if (message instanceof HttpMessage requestMessage) {
            logger.debug("{}: Start of sending message. \nMessage - {}", message.UUID, message);
            try {
                HttpResponse response;
                final String uri = SERVER_URL + destination;
                switch (requestMessage.httpMethod) {
                    case GET -> response = executeNotEnclosingRequest(requestMessage, destination, new HttpGet(uri));
                    case POST -> response = executeEnclosingRequest(requestMessage, destination, new HttpPost(uri));
                    case DELETE -> response = executeNotEnclosingRequest(requestMessage, destination, new HttpDelete(uri));
                    case PUT -> response = executeEnclosingRequest(requestMessage, destination, new HttpPut(uri));
                    case PATCH -> response = executeEnclosingRequest(requestMessage, destination, new HttpPatch(uri));
                    default -> throw new IllegalArgumentException("Expected + " + Arrays.toString(HttpMethod.values()) +
                            ", but found - " + requestMessage.httpMethod);
                }
                logger.debug("{}: Sending message was successful.", message.UUID);
                return new Message(
                        HeadersUtil.toNativeHeaders(response.getAllHeaders()),
                        new String(response.getEntity().getContent().readAllBytes()));
            } catch (IOException e) {
                logger.error(message.UUID + ": Http message sending error", e);
                throw new HttpException(e);
            }
        }
        throw new IllegalArgumentException("Expected HttpMessage");
    }

    @Override
    public void subscribe(Runnable callback, String destination) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    private HttpResponse executeNotEnclosingRequest(HttpMessage requestMessage, String destination, HttpRequestBase request) throws IOException {
        logger.debug("{}: Executing request without body", requestMessage.UUID);
        if (requestMessage.headers != null)
            request.setHeaders(HeadersUtil.toApacheHttpHeaders(requestMessage.headers));
        return httpClient.execute(request);
    }

    private HttpResponse executeEnclosingRequest(HttpMessage requestMessage, String destination, HttpEntityEnclosingRequestBase request) throws IOException {
        logger.debug("{}: Executing request with body", requestMessage.UUID);
        request.setEntity(new StringEntity(requestMessage.body, ContentType.APPLICATION_JSON));
        if (requestMessage.headers != null)
            request.setHeaders(HeadersUtil.toApacheHttpHeaders(requestMessage.headers));
        return httpClient.execute(request);
    }
}
