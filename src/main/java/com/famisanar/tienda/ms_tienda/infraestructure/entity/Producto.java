package com.famisanar.tienda.ms_tienda.infraestructure.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    public enum TipoProducto {
        PAPELERIA,
        SUPERMERCADO,
        DROGUERIA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipo;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "minimo", nullable = false)
    private Integer minimoStock;

    @Column(name = "precio_base", nullable = false, precision = 10, scale = 2)
    private Double precioBase;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Método para calcular el precio final con impuestos
    public Double getPrecioFinal() {
        switch (this.tipo) {
            case PAPELERIA:
                return precioBase * 1.16;
            case SUPERMERCADO:
                return precioBase * 1.04;
            case DROGUERIA:
                return precioBase * 1.12;
            default:
                return precioBase;
        }
    }

    // Método para verificar si necesita reorden
    public boolean necesitaPedido() {
        return cantidad <= minimoStock;
    }
}