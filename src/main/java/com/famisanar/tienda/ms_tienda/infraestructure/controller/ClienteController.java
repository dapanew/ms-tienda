package com.famisanar.tienda.ms_tienda.infraestructure.controller;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Cliente;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ApiResponse;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ClienteDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Cliente>>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(clientes, "Clientes obtenidos exitosamente", true));
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<ApiResponse<Cliente>> getClienteById(@PathVariable String identificacion) {
        Cliente cliente = clienteService.findById(identificacion);
        return ResponseEntity.ok(new ApiResponse<>(cliente, "Cliente obtenido exitosamente", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente nuevoCliente = clienteService.create(clienteDTO);
        return new ResponseEntity<>(
                new ApiResponse<>(nuevoCliente, "Cliente creado exitosamente", true),
                HttpStatus.CREATED);
    }

    @PutMapping("/{identificacion}")
    public ResponseEntity<ApiResponse<Cliente>> updateCliente(
            @PathVariable String identificacion, @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteActualizado = clienteService.update(identificacion, clienteDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(clienteActualizado, "Cliente actualizado exitosamente", true));
    }
/* 
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<ApiResponse<Void>> deleteCliente(@PathVariable String identificacion) {
        clienteService(identificacion);
        return ResponseEntity.ok(
                new ApiResponse<>(null, "Cliente eliminado exitosamente", true));
    }
*/
    }