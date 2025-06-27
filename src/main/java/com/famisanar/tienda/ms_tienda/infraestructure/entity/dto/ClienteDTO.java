package com.famisanar.tienda.ms_tienda.infraestructure.entity.dto;


import lombok.Data;

@Data
public class ClienteDTO {
    private String identificacion;
    private String nombre;
    private String apellidos;
    private String correo;
    private String usuario;
    private String clave;
}