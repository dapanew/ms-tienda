package com.famisanar.tienda.ms_tienda.infraestructure.services;



import  com.famisanar.tienda.ms_tienda.infraestructure.entity.Producto;
import com.famisanar.tienda.ms_tienda.infraestructure.entity.dto.ProductoDTO;
import  com.famisanar.tienda.ms_tienda.infraestructure.exception.ProductoException;
import com.famisanar.tienda.ms_tienda.infraestructure.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto no encontrado"));
    }

    @Transactional
    public Producto create(ProductoDTO productoDTO) {
        if (productoRepository.existsByNombre(productoDTO.getNombre())) {
            throw new ProductoException("Ya existe un producto con ese nombre");
        }

        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .tipo(productoDTO.getTipo())
                .cantidad(productoDTO.getCantidad())
                .minimoStock(productoDTO.getMinimoStock())
                .precioBase(productoDTO.getPrecioBase())
                .build();

        return productoRepository.save(producto);
    }

    @Transactional
    public Producto update(Long id, ProductoDTO productoDTO) {
        Producto producto = findById(id);
        
        producto.setNombre(productoDTO.getNombre());
        producto.setTipo(productoDTO.getTipo());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setMinimoStock(productoDTO.getMinimoStock());
        producto.setPrecioBase(productoDTO.getPrecioBase());
        
        return productoRepository.save(producto);
    }

    @Transactional
    public void delete(Long id) {
        Producto producto = findById(id);
        productoRepository.delete(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> getProductosParaReponer() {
        return productoRepository.findProductosParaReponer();
    }
}