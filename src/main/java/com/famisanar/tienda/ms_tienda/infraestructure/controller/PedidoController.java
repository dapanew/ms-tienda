package com.famisanar.tienda.ms_tienda.infraestructure.controller;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Pedido;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ApiResponse;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.PedidoDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<ApiResponse<Pedido>> crearPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.crearPedido(pedidoDTO);
        return new ResponseEntity<>(
                new ApiResponse<>(pedido, "Pedido creado exitosamente", true),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}/recibir")
    public ResponseEntity<ApiResponse<Pedido>> recibirPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.recibirPedido(id);
        return ResponseEntity.ok(
                new ApiResponse<>(pedido, "Pedido recibido exitosamente", true));
    }

    @GetMapping("/pendientes")
    public ResponseEntity<ApiResponse<List<Pedido>>> getPedidosPendientes() {
        List<Pedido> pedidos = pedidoService.getPedidosPendientes();
        return ResponseEntity.ok(
                new ApiResponse<>(pedidos, "Pedidos pendientes obtenidos exitosamente", true));
    }
}