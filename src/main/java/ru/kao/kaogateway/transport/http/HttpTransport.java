package ru.kao.kaogateway.transport.http;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kao.kaogateway.transport.Transport;

@Component
public class HttpTransport implements Transport {
    public HttpTransport(@Value("${KAO.gateway.http.server.url}") String serverUrl) {
        this.SERVER_URL = serverUrl;
    }

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    private final String SERVER_URL;

    @Override
    public Object send() {
//        HttpPost request = new HttpPost(SERVER_URL);
//        request.setEntity();
//        httpClient.execute(request, response -> {
//
//        });
        throw new UnsupportedOperationException();
    }
}
