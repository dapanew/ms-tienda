package com.famisanar.tienda.ms_tienda.infraestructure.controller;



import com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ApiResponse;
import  com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ProductoDTO;
import com.famisanar.tienda.ms_tienda.infraestructure.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(productos, "Productos obtenidos exitosamente", true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(producto, "Producto obtenido exitosamente", true));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> createProducto(@RequestBody ProductoDTO productoDTO) {
        Producto nuevoProducto = productoService.create(productoDTO);
        return new ResponseEntity<>(
                new ApiResponse<>(nuevoProducto, "Producto creado exitosamente", true),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> updateProducto(
            @PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        Producto productoActualizado = productoService.update(id, productoDTO);
        return ResponseEntity.ok(
                new ApiResponse<>(productoActualizado, "Producto actualizado exitosamente", true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProducto(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(null, "Producto eliminado exitosamente", true));
    }

    @GetMapping("/reponer")
    public ResponseEntity<ApiResponse<List<Producto>>> getProductosParaReponer() {
        List<Producto> productos = productoService.getProductosParaReponer();
        return ResponseEntity.ok(
                new ApiResponse<>(productos, "Productos para reponer obtenidos exitosamente", true));
    }
}
