package org.flab.api.global.cache;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {

    private final String  host;

    private final int port;

    public RedisProperties(String host, int port) {
        this.host = host;
        this.port = port;
    }

}
