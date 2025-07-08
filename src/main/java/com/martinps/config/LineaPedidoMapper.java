package com.martinps.config;

import com.martinps.dto.LineaPedidoDTO;
import com.martinps.model.LineaPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LineaPedidoMapper {
    LineaPedidoDTO toDto(LineaPedido lineaPedido);
    LineaPedido toEntity(LineaPedidoDTO lineaPedidoDTO);

}
