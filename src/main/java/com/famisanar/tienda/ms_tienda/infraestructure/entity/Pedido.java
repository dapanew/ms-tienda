package com.famisanar.tienda.ms_tienda.infraestructure.entity;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    public enum EstadoPedido {
        PENDIENTE,
        RECIBIDO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Integer cantidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}