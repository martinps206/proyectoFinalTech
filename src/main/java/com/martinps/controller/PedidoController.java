package com.martinps.controller;

import com.martinps.config.PedidoMapper;
import com.martinps.model.Pedido;
import com.martinps.request.PedidoRequest;
import com.martinps.response.PedidoResponse;
import com.martinps.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;

    public PedidoController(PedidoService service, PedidoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return service.listar().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    public PedidoResponse crear(@RequestBody @Valid PedidoRequest request) {
        Pedido p = mapper.toEntity(request);
        Pedido creado = service.guardar(p);
        return mapper.toDto(creado);
    }
}
