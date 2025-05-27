package com.perfulandia.producto_service; // Define el paquete base de tu aplicación

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotación clave de Spring Boot
public class ProductoServiceApplication {

    public static void main(String[] args) {
        // Inicia la aplicación Spring Boot
        SpringApplication.run(ProductoServiceApplication.class, args);
        System.out.println("Microservicio de Productos Perfulandia iniciado y listo!");
    }
}