package com.martinps.dto;

import com.martinps.model.EstadoPedido;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {
    private Long id;
    private LocalDateTime fecha;
    private EstadoPedido estado;
    @NotEmpty
    List<LineaPedidoDTO> lineas;
    private double total;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
