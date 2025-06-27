package com.famisanar.tienda.ms_tienda.infraestructure.controller;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ApiResponse;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.EstadisticasDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.services.EstadisticasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estadisticas")
@RequiredArgsConstructor
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    @GetMapping
    public ResponseEntity<ApiResponse<EstadisticasDTO>> getEstadisticas() {
        EstadisticasDTO estadisticas = estadisticasService.getEstadisticas();
        return ResponseEntity.ok(
                new ApiResponse<>(estadisticas, "Estadísticas obtenidas exitosamente", true));
    }
}