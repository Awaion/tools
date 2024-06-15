package com.awaion.demo019;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RpcApiConfiguration {
    @Bean
    HttpServiceProxyFactory httpServiceProxyFactory(@Value("${api.token}") String token) {
        WebClient client = WebClient.builder()
                .defaultHeader("Authorization", token)
                .codecs(clientCodecConfigurer
                        -> clientCodecConfigurer
                        .defaultCodecs()
                        .maxInMemorySize(256 * 1024 * 1024))
                .build();
        return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
    }

    @Bean
    RpcDemo019Interface rpcDemo019Interface(HttpServiceProxyFactory httpServiceProxyFactory) {
        return httpServiceProxyFactory.createClient(RpcDemo019Interface.class);
    }

}