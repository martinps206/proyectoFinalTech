package com.martinps.dto.response;

import com.martinps.dto.LineaPedidoDTO;
import com.martinps.model.EstadoPedido;
import com.martinps.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponse {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPedido estado;
    private List<LineaPedidoDTO> lineas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<LineaPedidoDTO> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoDTO> lineas) {
        this.lineas = lineas;
    }
}

