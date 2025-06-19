package com.lizbeth.inventario_productos;

import com.lizbeth.inventario.model.Producto;
import com.lizbeth.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioProductosApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductoRepository repo) {
        return args -> {
            // Insertar productos
            repo.save(new Producto("Laptop Lenovo", "Computadora potente", 12500));
            repo.save(new Producto("Mouse Logitech", "Inalámbrico", 350));
            repo.save(new Producto("Teclado Mecánico", "Switch azul", 950));
            repo.save(new Producto("Monitor", "27 pulgadas", 3200));

            // Consultas
            System.out.println("📦 Productos con precio mayor a 500:");
            repo.findByPrecioGreaterThan(500).forEach(System.out::println);

            System.out.println("\n🔍 Productos que contienen 'lap':");
            repo.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

            System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
            repo.findByPrecioBetween(400, 1000).forEach(System.out::println);

            System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
            repo.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}