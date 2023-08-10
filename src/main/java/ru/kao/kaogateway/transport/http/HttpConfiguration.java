package ru.kao.kaogateway.transport.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfiguration {

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }
}
