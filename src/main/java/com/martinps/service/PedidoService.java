package com.martinps.service;

import com.martinps.dto.request.PedidoRequest;
import com.martinps.exception.ResourceNotFoundException;
import com.martinps.exception.StockInsuficienteException;
import com.martinps.model.EstadoPedido;
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

    @Transactional()
    public List<Pedido> listar() {
        return pedidoRepo.findAllWithLineas();
    }


    @Transactional
    public Pedido guardar(Pedido pedido) {
        if (pedido.getLineas() == null || pedido.getLineas().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos una línea");
        }

        for (LineaPedido linea : pedido.getLineas()) {
            // Recuperar el producto gestionado por Hibernate
            Producto producto = productoService.buscarPorId(linea.getProducto().getId());

            // Asociar la instancia gestionada a la línea
            linea.setProducto(producto);

            // Verificar stock
            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
            }
        }
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidoRepo.save(pedido);
    }

    @Transactional
    public Pedido actualizar(Long id, PedidoRequest request) {
        Pedido existente = pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));

        existente.getLineas().clear();

        List<LineaPedido> nuevasLineas = request.getLineas().stream()
                .map(lineaReq -> {
                    Producto producto = productoService.buscarPorId(lineaReq.getProducto().getId());
                    LineaPedido linea = new LineaPedido();
                    linea.setProducto(producto);
                    linea.setCantidad(lineaReq.getCantidad());
                    linea.setPedido(existente);
                    return linea;
                }).toList();

        existente.setLineas(nuevasLineas);
        existente.setEstado(EstadoPedido.PENDIENTE); // o el estado actual

        return pedidoRepo.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
        pedidoRepo.delete(pedido);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + id));
    }

    public List<Pedido> buscarPorEstado(EstadoPedido estado) {
        return pedidoRepo.findByEstado(estado);
    }

}
