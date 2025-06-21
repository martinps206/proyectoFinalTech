package com.martinps.config;

import com.martinps.model.Pedido;
import com.martinps.request.PedidoRequest;
import com.martinps.response.PedidoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoResponse toDto(Pedido pedido);

    Pedido toEntity(PedidoRequest request);
}
