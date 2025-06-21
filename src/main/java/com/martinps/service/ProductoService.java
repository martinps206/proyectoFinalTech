package com.martinps.service;

import com.martinps.exception.ResourceNotFoundException;
import com.martinps.model.Producto;
import com.martinps.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public Producto actualizar(Long id, Producto nuevo) {
        Producto p = buscarPorId(id);
        p.setNombre(nuevo.getNombre());
        p.setDescripcion(nuevo.getDescripcion());
        p.setPrecio(nuevo.getPrecio());
        p.setCategoria(nuevo.getCategoria());
        p.setImagenUrl(nuevo.getImagenUrl());
        p.setStock(nuevo.getStock());
        return repo.save(p);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
