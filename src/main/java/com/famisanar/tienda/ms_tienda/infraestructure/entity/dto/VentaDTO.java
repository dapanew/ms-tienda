package com.famisanar.tienda.ms_tienda.infraestructure.entity.dto;



import lombok.Data;

@Data
public class VentaDTO {
   private String clienteIdentificacion; // Cambiado para usar identificación en lugar de ID
    private Long productoId;
    private Integer cantidad;
}
