package com.martinps.controller;

import com.martinps.model.Pedido;
import com.martinps.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return service.crearPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }
}