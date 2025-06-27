package com.famisanar.tienda.ms_tienda.infraestructure.entity;



import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @Column(name = "identificacion", length = 20)
    private String identificacion;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(nullable = false, unique = true, length = 30)
    private String usuario;

    @Column(nullable = false, length = 100)
    private String clave;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;
}