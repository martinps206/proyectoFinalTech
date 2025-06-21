package com.martinps.config;

import com.martinps.model.Producto;
import com.martinps.request.ProductoRequest;
import com.martinps.response.ProductoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoResponse toDto(Producto producto);

    Producto toEntity(ProductoRequest request);
}
