package com.martinps.controller;

import com.martinps.config.ProductoMapper;
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
    private final ProductoMapper mapper;

    public ProductoController(ProductoService service, ProductoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProductoResponse> listar() {
        return service.listar().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    public ProductoResponse crear(@RequestBody @Valid ProductoRequest request) {
        Producto p = mapper.toEntity(request);
        Producto creado = service.guardar(p);
        return mapper.toDto(creado);
    }
}
