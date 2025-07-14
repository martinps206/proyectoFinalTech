package com.martinps.controller;

import com.martinps.config.ProductoMapper;
import com.martinps.dto.request.ProductoRequest;
import com.martinps.dto.response.ProductoResponse;
import com.martinps.model.Producto;
import com.martinps.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarProductos() {
        Producto producto = new Producto();
        ProductoResponse response = new ProductoResponse();

        when(productoService.listar()).thenReturn(List.of(producto));
        when(productoMapper.toDto(producto)).thenReturn(response);

        ResponseEntity<List<ProductoResponse>> result = productoController.listar();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void testCrearProducto() {
        ProductoRequest request = new ProductoRequest();
        Producto producto = new Producto();
        Producto creado = new Producto();
        ProductoResponse response = new ProductoResponse();

        when(productoMapper.toEntity(request)).thenReturn(producto);
        when(productoService.guardar(producto)).thenReturn(creado);
        when(productoMapper.toDto(creado)).thenReturn(response);

        ResponseEntity<ProductoResponse> result = productoController.crear(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testActualizarProducto() {
        Long id = 1L;
        ProductoRequest request = new ProductoRequest();
        Producto actualizado = new Producto();
        ProductoResponse response = new ProductoResponse();

        when(productoService.actualizar(id, request)).thenReturn(actualizado);
        when(productoMapper.toDto(actualizado)).thenReturn(response);

        ResponseEntity<ProductoResponse> result = productoController.actualizar(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testEliminarProducto() {
        Long id = 1L;

        ResponseEntity<Void> result = productoController.eliminar(id);

        verify(productoService, times(1)).eliminar(id);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testBuscarPorCategoria() {
        String categoria = "ELECTRONICA";
        Producto producto = new Producto();
        ProductoResponse response = new ProductoResponse();

        when(productoService.buscarPorCategoria(categoria)).thenReturn(List.of(producto));
        when(productoMapper.toDto(producto)).thenReturn(response);

        ResponseEntity<List<ProductoResponse>> result = productoController.buscarPorCategoria(categoria);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void testActualizarStock() {
        Long id = 1L;
        int nuevoStock = 10;
        Producto actualizado = new Producto();
        ProductoResponse response = new ProductoResponse();

        when(productoService.actualizarStock(id, nuevoStock)).thenReturn(actualizado);
        when(productoMapper.toDto(actualizado)).thenReturn(response);

        ResponseEntity<ProductoResponse> result = productoController.actualizarStock(id, nuevoStock);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
