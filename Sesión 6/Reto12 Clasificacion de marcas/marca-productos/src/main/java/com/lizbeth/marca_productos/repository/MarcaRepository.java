package com.lizbeth.marca_productos.repository;

import com.lizbeth.inventario.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {}
