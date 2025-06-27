package com.famisanar.tienda.ms_tienda.infraestructure.repository;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByUsuario(String usuario);
    Optional<Cliente> findByCorreo(String correo);
    boolean existsByUsuario(String usuario);
    boolean existsByCorreo(String correo);
}