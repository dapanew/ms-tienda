package com.famisanar.tienda.ms_tienda.infraestructure.services;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Pedido;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.PedidoDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.exception.ProductoException;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.PedidoRepository;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    public Pedido crearPedido(PedidoDTO pedidoDTO) {
        Producto producto = productoRepository.findById(pedidoDTO.getProductoId())
                .orElseThrow(() -> new ProductoException("Producto no encontrado"));

        Pedido pedido = Pedido.builder()
                .producto(producto)
                .cantidad(pedidoDTO.getCantidad())
                .build();

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido recibirPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Pedido no encontrado"));

        if (pedido.getEstado() != Pedido.EstadoPedido.PENDIENTE) {
            throw new ProductoException("Solo se pueden recibir pedidos pendientes");
        }

        // Actualizar stock del producto
        Producto producto = pedido.getProducto();
        producto.setCantidad(producto.getCantidad() + pedido.getCantidad());
        productoRepository.save(producto);

        // Marcar pedido como recibido
        pedido.setEstado(Pedido.EstadoPedido.RECIBIDO);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> getPedidosPendientes() {
        return pedidoRepository.findByEstado(Pedido.EstadoPedido.PENDIENTE);
    }
}