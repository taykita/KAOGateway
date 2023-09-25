package ru.kao.kaogateway.configuration;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kao.kaogateway.transport.http.HttpTransport;

@Configuration
public class HttpTransportConfig {

    @Bean
    @Qualifier("transportForGateway")
    public HttpTransport httpTransportForGateway(@Value("${ru.kao.gateway.http.gateway.url}") String serverUrl,
                         HttpClient httpClient) {
        return new HttpTransport(serverUrl, httpClient);
    }

    @Bean
    @Qualifier("transportForServer")
    public HttpTransport httpTransportForServer(@Value("${ru.kao.gateway.http.server.url}") String serverUrl,
                                       HttpClient httpClient) {
        return new HttpTransport(serverUrl, httpClient);
    }

}
