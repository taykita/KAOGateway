package ru.kao.kaogateway.transport.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kao.kaogateway.dto.Message;
import ru.kao.kaogateway.exception.HttpException;
import ru.kao.kaogateway.transport.Transport;
import ru.kao.kaogateway.util.HeadersUtil;
import ru.kao.kaogateway.util.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Component
public class HttpTransport implements Transport {
    private final Logger logger = LoggerUtil.getLogger(HttpTransport.class);

    @Autowired
    public HttpTransport(@Value("${KAO.gateway.http.server.url}") String serverUrl,
                         HttpClient httpClient) {
        this.SERVER_URL = serverUrl;
        this.httpClient = httpClient;
    }

    private final String SERVER_URL;
    private final HttpClient httpClient;

    @Override
    public Message send(Message requestMessage, String destination) throws HttpException {
        try {
            HttpPost request = new HttpPost(SERVER_URL);
            request.setURI(URI.create(SERVER_URL + destination));
            request.setEntity(new ByteArrayEntity(requestMessage.body, ContentType.APPLICATION_JSON));
            request.setHeaders(HeadersUtil.toApacheHttpHeaders(requestMessage.headers));

            HttpResponse response = httpClient.execute(request);

            return new Message(
                    HeadersUtil.toNativeHeaders(response.getAllHeaders()),
                    response.getEntity().getContent().readAllBytes());
        } catch (IOException e) {
            logger.error("Http message sending error", e);
            throw new HttpException(e);
        }
    }
}
