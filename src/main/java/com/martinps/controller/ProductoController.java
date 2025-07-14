package com.martinps.controller;

import com.martinps.config.ProductoMapper;
import com.martinps.model.Producto;
import com.martinps.dto.request.ProductoRequest;
import com.martinps.dto.response.ProductoResponse;
import com.martinps.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductoResponse>> listar() {
        List<ProductoResponse> response = service.listar().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@RequestBody @Valid ProductoRequest request) {
        Producto producto = mapper.toEntity(request);
        Producto creado = service.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProductoRequest request) {
        Producto actualizado = service.actualizar(id, request);
        return ResponseEntity.ok(mapper.toDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponse>> buscarPorCategoria(@PathVariable String categoria) {
        List<Producto> productos = service.buscarPorCategoria(categoria.toUpperCase());
        List<ProductoResponse> response = productos.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponse> actualizarStock(
            @PathVariable Long id,
            @RequestParam("stock") int nuevoStock) {
        Producto actualizado = service.actualizarStock(id, nuevoStock);
        return ResponseEntity.ok(mapper.toDto(actualizado));
    }


}
