package com.martinps.dto;

import jakarta.validation.constraints.Min;

public class LineaPedidoDTO {

    private Long productoId;
    @Min(1) int cantidad;

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
