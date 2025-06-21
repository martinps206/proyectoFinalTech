package com.martinps.controller;

import com.martinps.model.Producto;
import com.martinps.request.ProductoRequest;
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
    public List<Producto> listar() {
        return service.listar();
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
