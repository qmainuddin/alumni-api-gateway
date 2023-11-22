package com.miu.projects.alumniapigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override

    
public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
 
{
        log.info("Mainuddin Request: {} - {} - {}", exchange.getRequest().getMethod(), exchange.getRequest().getURI(), exchange.getRequest().getHeaders());

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("Response: {} - {} - {}", exchange.getResponse().getStatusCode(), exchange.getResponse().getHeaders(), exchange.getResponse().getCookies().toString());
        }));
    }
}