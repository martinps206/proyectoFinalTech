package com.martinps.service;

import com.martinps.exception.StockInsuficienteException;
import com.martinps.model.LineaPedido;
import com.martinps.model.Pedido;
import com.martinps.model.Producto;
import com.martinps.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProductoService productoService;

    public PedidoService(PedidoRepository pedidoRepo, ProductoService productoService) {
        this.pedidoRepo = pedidoRepo;
        this.productoService = productoService;
    }

    @Transactional
    public Pedido crearPedido(Pedido pedido) {
        if (pedido.getLineas() == null || pedido.getLineas().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos una línea");
        }

        for (LineaPedido linea : pedido.getLineas()) {
            Producto p = productoService.buscarPorId(linea.getProducto().getId());

            if (p.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + p.getNombre());
            }

            p.setStock(p.getStock() - linea.getCantidad());
            productoService.guardar(p);
        }

        return pedidoRepo.save(pedido);
    }

    @Transactional()
    public List<Pedido> listar() {
        return pedidoRepo.findAll();
    }
}
