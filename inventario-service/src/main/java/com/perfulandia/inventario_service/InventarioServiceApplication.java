package com.perfulandia.inventario_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
        System.out.println("Microservicio de Inventario Perfulandia iniciado y listo en puerto 8081!");
    }
}