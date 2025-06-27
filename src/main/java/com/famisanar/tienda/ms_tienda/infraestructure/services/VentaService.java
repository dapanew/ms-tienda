package com.famisanar.tienda.ms_tienda.infraestructure.services;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Cliente;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.Venta;
import  com.famisanar.tienda.ms_tienda.infraestructure.exception.ClienteException;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.VentaDTO;
import  com.famisanar.tienda.ms_tienda.infraestructure.exception.ProductoException;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.ClienteRepository;
import  com.famisanar.tienda.ms_tienda.infraestructure.repository.ProductoRepository;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
     public Venta realizarVenta(VentaDTO ventaDTO) {
        Producto producto = productoRepository.findById(ventaDTO.getProductoId())
                .orElseThrow(() -> new ProductoException("Producto no encontrado"));

        Cliente cliente = clienteRepository.findById(ventaDTO.getClienteIdentificacion())
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

        if (producto.getCantidad() < ventaDTO.getCantidad()) {
            throw new ProductoException("No hay suficiente stock disponible");
        }

        // Actualizar stock
        producto.setCantidad(producto.getCantidad() - ventaDTO.getCantidad());
        productoRepository.save(producto);

        // Calcular total
        double precioUnitario = producto.getPrecioFinal();
        double total = precioUnitario * ventaDTO.getCantidad();

        Venta venta = Venta.builder()
                .producto(producto)
                .cliente(cliente)
                .cantidad(ventaDTO.getCantidad())
                .precioUnitario(precioUnitario)
                .total(total)
                .build();

        return ventaRepository.save(venta);
    }

    @Transactional(readOnly = true)
    public List<Venta> getVentasPorPeriodo(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public Double getTotalVentas() {
        return ventaRepository.getTotalVentas();
    }
}