package com.martinps.controller;

import com.martinps.config.PedidoMapper;
import com.martinps.dto.request.PedidoRequest;
import com.martinps.dto.response.PedidoResponse;
import com.martinps.model.EstadoPedido;
import com.martinps.model.Pedido;
import com.martinps.model.LineaPedido;
import com.martinps.model.Producto;
import com.martinps.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarPedidos() {
        Pedido pedido = new Pedido();
        PedidoResponse response = new PedidoResponse();

        when(pedidoService.listar()).thenReturn(List.of(pedido));
        when(pedidoMapper.toDto(pedido)).thenReturn(response);

        ResponseEntity<List<PedidoResponse>> result = pedidoController.listar();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void testCrearPedido() {
        PedidoRequest request = new PedidoRequest();
        Pedido pedido = new Pedido();
        Pedido creado = new Pedido();
        PedidoResponse response = new PedidoResponse();

        when(pedidoMapper.toEntity(request)).thenReturn(pedido);
        when(pedidoService.guardar(pedido)).thenReturn(creado);
        when(pedidoMapper.toDto(creado)).thenReturn(response);

        ResponseEntity<PedidoResponse> result = pedidoController.crear(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testActualizarPedido() {
        Long id = 1L;
        PedidoRequest request = new PedidoRequest();
        Pedido actualizado = new Pedido();
        PedidoResponse response = new PedidoResponse();

        when(pedidoService.actualizar(id, request)).thenReturn(actualizado);
        when(pedidoMapper.toDto(actualizado)).thenReturn(response);

        ResponseEntity<PedidoResponse> result = pedidoController.actualizar(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testEliminarPedido() {
        Long id = 1L;

        ResponseEntity<Void> result = pedidoController.eliminar(id);

        verify(pedidoService, times(1)).eliminar(id);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void testBuscarPorEstado() {
        EstadoPedido estado = EstadoPedido.PENDIENTE;
        Pedido pedido = new Pedido();
        PedidoResponse response = new PedidoResponse();

        when(pedidoService.buscarPorEstado(estado)).thenReturn(List.of(pedido));
        when(pedidoMapper.toDto(pedido)).thenReturn(response);

        ResponseEntity<List<PedidoResponse>> result = pedidoController.buscarPorEstado(estado.name());

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void testObtenerResumenPedido() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setFecha(LocalDate.now().atStartOfDay());
        pedido.setEstado(EstadoPedido.PENDIENTE);

        Producto producto = new Producto();
        producto.setPrecio(100.0);
        LineaPedido linea = new LineaPedido();
        linea.setCantidad(2);
        linea.setProducto(producto);
        pedido.setLineas(List.of(linea));

        when(pedidoService.buscarPorId(id)).thenReturn(pedido);

        ResponseEntity<?> result = pedidoController.obtenerResumenPedido(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Map<?, ?> resumen = (Map<?, ?>) result.getBody();
        assertEquals(200.0, resumen.get("total"));
        assertEquals(2, resumen.get("cantidadItems"));
    }
}
