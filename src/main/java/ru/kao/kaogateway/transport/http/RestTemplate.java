package ru.kao.kaogateway.transport.http;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import ru.kao.kaogateway.util.LoggerUtil;

@Component
public class RestTemplate {
    private final Logger logger = LoggerUtil.getLogger(RestTemplate.class);

    public RestTemplate() {

    }

    @PreDestroy
    public void destroy() {
        logger.debug("Destroying Rest Template");
    }
}
