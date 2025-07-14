package com.martinps.controller;

import com.martinps.config.PedidoMapper;
import com.martinps.model.EstadoPedido;
import com.martinps.model.Pedido;
import com.martinps.dto.request.PedidoRequest;
import com.martinps.dto.response.PedidoResponse;
import com.martinps.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<PedidoResponse>> listar() {
        List<PedidoResponse> response = service.listar().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> crear(@RequestBody @Valid PedidoRequest request) {
        Pedido pedido = mapper.toEntity(request);
        Pedido creado = service.guardar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid PedidoRequest request) {
        Pedido actualizado = service.actualizar(id, request);
        return ResponseEntity.ok(mapper.toDto(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<PedidoResponse>> buscarPorEstado(@PathVariable String estado) {
        List<Pedido> pedidos = service.buscarPorEstado(EstadoPedido.valueOf(estado.toUpperCase()));
        List<PedidoResponse> response = pedidos.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/resumen")
    public ResponseEntity<?> obtenerResumenPedido(@PathVariable Long id) {
        Pedido pedido = service.buscarPorId(id);

        int cantidadItems = pedido.getLineas().stream()
                .mapToInt(lp -> lp.getCantidad())
                .sum();

        double total = pedido.getLineas().stream()
                .mapToDouble(lp -> lp.getProducto().getPrecio() * lp.getCantidad())
                .sum();

        return ResponseEntity.ok(Map.of(
                "pedidoId", pedido.getId(),
                "fecha", pedido.getFecha(),
                "estado", pedido.getEstado(),
                "cantidadItems", cantidadItems,
                "total", total
        ));
    }



}
