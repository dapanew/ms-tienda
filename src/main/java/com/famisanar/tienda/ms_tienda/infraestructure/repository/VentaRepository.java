package com.famisanar.tienda.ms_tienda.infraestructure.repository;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT v.producto, SUM(v.cantidad) as total FROM Venta v GROUP BY v.producto ORDER BY total DESC")
    List<Object[]> findProductosMasVendidos();
    
    @Query("SELECT v.producto, SUM(v.cantidad) as total FROM Venta v GROUP BY v.producto ORDER BY total ASC")
    List<Object[]> findProductosMenosVendidos();
    
    @Query("SELECT COALESCE(SUM(v.total), 0) FROM Venta v")
    Double getTotalVentas();
}
