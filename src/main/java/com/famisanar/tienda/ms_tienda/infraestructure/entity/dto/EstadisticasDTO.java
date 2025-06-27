package com.famisanar.tienda.ms_tienda.infraestructure.entity.dto;



import lombok.Data;

@Data
public class EstadisticasDTO {
    private String productoMasVendido;
    private String productoMenosVendido;
    private Double totalVentas;
    private Double promedioVentas;
}