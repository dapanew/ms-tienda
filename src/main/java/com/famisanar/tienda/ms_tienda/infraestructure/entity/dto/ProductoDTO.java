package com.famisanar.tienda.ms_tienda.infraestructure.entity.dto;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private Producto.TipoProducto tipo;
    private Integer cantidad;
    private Integer minimoStock;
    private Double precioBase;
}