package com.Perfulandia.Clientes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class WebClientConfig {
    @Value("${producto.service.baseurl}")
    private String productoServiceBaseUrl;

    @Bean(name = "productoWebClient")
    public WebClient productoWebClient() {
        return WebClient.builder()
                .baseUrl(productoServiceBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
