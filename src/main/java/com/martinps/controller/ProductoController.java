package com.martinps.controller;

import com.martinps.model.Producto;
import com.martinps.request.ProductoRequest;
import com.martinps.response.ProductoResponse;
import com.martinps.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }


    @GetMapping
    public List<ProductoResponse> listar() {
        return service.listar().stream().map(this::toResponse).toList();
    }

    private ProductoResponse toResponse(Producto p) {
        ProductoResponse dto = new ProductoResponse();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setCategoria(p.getCategoria());
        dto.setImagenUrl(p.getImagenUrl());
        dto.setStock(p.getStock());
        return dto;
    }

    @GetMapping("/{id}")
    public Producto get(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Producto crear(@RequestBody @Valid ProductoRequest request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        return service.guardar(p);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
