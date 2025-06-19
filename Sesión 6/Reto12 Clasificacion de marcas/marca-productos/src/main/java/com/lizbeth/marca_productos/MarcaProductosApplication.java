package com.lizbeth.marca_productos;

import com.lizbeth.inventario.model.Marca;
import com.lizbeth.inventario.model.Producto;
import com.lizbeth.inventario.repository.MarcaRepository;
import com.lizbeth.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarcaProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcaProductosApplication.class, args);
	}

	@Bean
	CommandLineRunner init(MarcaRepository marcaRepo, ProductoRepository productoRepo) {
		return args -> {
			Marca apple = new Marca("Apple");
			Marca samsung = new Marca("Samsung");
			marcaRepo.save(apple);
			marcaRepo.save(samsung);

			productoRepo.save(new Producto("iPhone 15", "Teléfono Apple", 25000, apple));
			productoRepo.save(new Producto("iPad Pro", "Tablet Apple", 18000, apple));
			productoRepo.save(new Producto("Galaxy S23", "Teléfono Samsung", 22000, samsung));
			productoRepo.save(new Producto("Smart TV", "Televisión Samsung", 15000, samsung));

			System.out.println("📚 Productos por marca:");
			marcaRepo.findAll().forEach(marca -> {
				System.out.println("🏷️ " + marca.getNombre() + ":");
				productoRepo.findAll().stream()
					.filter(p -> p.getMarca().getId().equals(marca.getId()))
					.forEach(p -> System.out.println("   - " + p.getNombre()));
			});
		};
	}
}
