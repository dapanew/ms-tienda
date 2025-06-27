package com.famisanar.tienda.ms_tienda.infraestructure.repository;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Pedido;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.Pedido.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByProductoId(Long productoId);
}