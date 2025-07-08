package com.martinps.config;

import com.martinps.model.Pedido;
import com.martinps.dto.request.PedidoRequest;
import com.martinps.dto.response.PedidoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoResponse toDto(Pedido pedido);

    Pedido toEntity(PedidoRequest request);
}
