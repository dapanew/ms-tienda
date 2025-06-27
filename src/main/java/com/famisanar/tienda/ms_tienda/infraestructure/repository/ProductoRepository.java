package com.famisanar.tienda.ms_tienda.infraestructure.repository;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByTipo(Producto.TipoProducto tipo);
    
    @Query("SELECT p FROM Producto p WHERE p.cantidad <= p.minimoStock")
    List<Producto> findProductosParaReponer();
    
    boolean existsByNombre(String nombre);
}