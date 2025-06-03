package com.Perfulandia.Clientes;

import com.Perfulandia.Clientes.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class ProductoApiClient {
    private final WebClient webClient;

    public ProductoApiClient(@Qualifier("productoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductoDTO> obtenerProductoPorId(Long productoId) {
        return this.webClient.get()
                .uri("/productos/{id}", productoId)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.empty()
                )
                .onStatus(
                        httpStatus -> (httpStatus.is4xxClientError() && !httpStatus.equals(HttpStatus.NOT_FOUND)) || httpStatus.is5xxServerError(),
                        errorResponse -> errorResponse.bodyToMono(String.class)
                                .defaultIfEmpty("[Cuerpo del error no disponible o vacío]")
                                .flatMap(errorBodyString -> {
                                    String mensaje = String.format(
                                            "Error al obtener producto ID %d: Código %s - Detalles: %s", productoId, errorResponse.statusCode().toString(),
                                            errorBodyString
                                    );
                                    return Mono.error(new RuntimeException(mensaje));
                                })
                )
                .bodyToMono(ProductoDTO.class)
                .timeout(Duration.ofSeconds(5));
    }

    public Flux<ProductoDTO> obtenerTodosLosProductos() {
        return this.webClient.get()
                .uri("/productos")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        errorResponse -> errorResponse.bodyToMono(String.class)
                                .defaultIfEmpty("[Cuerpo del error no disponible o vacío]")
                                .flatMap(errorBodyString -> {
                                    String mensaje = String.format(
                                            "Error al obtener todos los productos: Código %s - Detalles: %s", errorResponse.statusCode().toString(), errorBodyString
                                    );
                                    return Mono.error(new RuntimeException(mensaje));
                                })
                )
                .bodyToFlux(ProductoDTO.class)
                .timeout(Duration.ofSeconds(10));
    }

    public Flux<ProductoDTO> obtenerProductosPorCategoria(String categoria) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/productos/categoria/{cat}").build(categoria))
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        errorResponse -> errorResponse.bodyToMono(String.class)
                                .defaultIfEmpty("[Cuerpo del error no disponible o vacío]")
                                .flatMap(errorBodyString -> {
                                    String mensaje = String.format(
                                            "Error al obtener productos por categoría '%s': Código %s - Detalles: %s", categoria, errorResponse.statusCode().toString(), errorBodyString
                                    );
                                    return Mono.error(new RuntimeException(mensaje));
                                })
                )
                .bodyToFlux(ProductoDTO.class)
                .timeout(Duration.ofSeconds(10));
    }
}
