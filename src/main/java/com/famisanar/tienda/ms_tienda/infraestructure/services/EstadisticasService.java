package com.famisanar.tienda.ms_tienda.infraestructure.services;


import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import  com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.EstadisticasDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadisticasService {

    private final VentaRepository ventaRepository;

    @Transactional(readOnly = true)
    public EstadisticasDTO getEstadisticas() {
        EstadisticasDTO estadisticas = new EstadisticasDTO();
        
        // Producto más vendido
        List<Object[]> masVendidos = ventaRepository.findProductosMasVendidos();
        if (!masVendidos.isEmpty()) {
            Producto producto = (Producto) masVendidos.get(0)[0];
            estadisticas.setProductoMasVendido(producto.getNombre());
        }
        
        // Producto menos vendido
        List<Object[]> menosVendidos = ventaRepository.findProductosMenosVendidos();
        if (!menosVendidos.isEmpty()) {
            Producto producto = (Producto) menosVendidos.get(0)[0];
            estadisticas.setProductoMenosVendido(producto.getNombre());
        }
        
        // Total ventas
        estadisticas.setTotalVentas(ventaRepository.getTotalVentas());
        
        // Promedio ventas
        Double totalVentas = estadisticas.getTotalVentas();
       // Double totalUnidades = ventaRepository.sumTotalCantidadVendida();
    //    if (totalUnidades != null && totalUnidades > 0) {
      //      estadisticas.setPromedioVentas(totalVentas / totalUnidades);
       // } else {
        //    estadisticas.setPromedioVentas(0.0);
        
        return estadisticas;
    }
}
