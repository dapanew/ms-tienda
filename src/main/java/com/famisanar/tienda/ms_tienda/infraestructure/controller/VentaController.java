package com.famisanar.tienda.ms_tienda.infraestructure.controller;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Venta;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ApiResponse;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.VentaDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<ApiResponse<Venta>> realizarVenta(@RequestBody VentaDTO ventaDTO) {
        Venta venta = ventaService.realizarVenta(ventaDTO);
        return new ResponseEntity<>(
                new ApiResponse<>(venta, "Venta realizada exitosamente", true),
                HttpStatus.CREATED);
    }

    @GetMapping("/periodo")
    public ResponseEntity<ApiResponse<List<Venta>>> getVentasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<Venta> ventas = ventaService.getVentasPorPeriodo(inicio, fin);
        return ResponseEntity.ok(
                new ApiResponse<>(ventas, "Ventas del periodo obtenidas exitosamente", true));
    }

    @GetMapping("/total")
    public ResponseEntity<ApiResponse<Double>> getTotalVentas() {
        Double total = ventaService.getTotalVentas();
        return ResponseEntity.ok(
                new ApiResponse<>(total, "Total de ventas obtenido exitosamente", true));
    }
}