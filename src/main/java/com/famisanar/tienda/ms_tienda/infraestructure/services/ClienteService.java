package com.famisanar.tienda.ms_tienda.infraestructure.services;

import com.famisanar.tienda.ms_tienda.infraestructure.entity.Cliente;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ClienteDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.exception.ClienteException;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente findById(String identificacion) {
        return clienteRepository.findById(identificacion)
                .orElseThrow(() -> new ClienteException("Cliente no encontrado"));
    }

    @Transactional
    public Cliente create(ClienteDTO clienteDTO) {
        if (clienteRepository.existsById(clienteDTO.getIdentificacion())) {
            throw new ClienteException("Ya existe un cliente con esta identificación");
        }
        if (clienteRepository.existsByUsuario(clienteDTO.getUsuario())) {
            throw new ClienteException("El nombre de usuario ya está en uso");
        }
        if (clienteRepository.existsByCorreo(clienteDTO.getCorreo())) {
            throw new ClienteException("El correo electrónico ya está registrado");
        }

        Cliente cliente = Cliente.builder()
                .identificacion(clienteDTO.getIdentificacion())
                .nombre(clienteDTO.getNombre())
                .apellidos(clienteDTO.getApellidos())
                .correo(clienteDTO.getCorreo())
                .usuario(clienteDTO.getUsuario())
                .clave(clienteDTO.getClave()) // Ahora se almacena en texto plano
                .build();

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente update(String identificacion, ClienteDTO clienteDTO) {
        Cliente cliente = findById(identificacion);
        
        if (!cliente.getUsuario().equals(clienteDTO.getUsuario())) {
            if (clienteRepository.existsByUsuario(clienteDTO.getUsuario())) {
                throw new ClienteException("El nombre de usuario ya está en uso");
            }
        }
        
        if (!cliente.getCorreo().equals(clienteDTO.getCorreo())) {
            if (clienteRepository.existsByCorreo(clienteDTO.getCorreo())) {
                throw new ClienteException("El correo electrónico ya está registrado");
            }
        }

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setUsuario(clienteDTO.getUsuario());
        cliente.setClave(clienteDTO.getClave()); // Actualiza con texto plano
        
        return clienteRepository.save(cliente);
    }

    // ... otros métodos ...
}