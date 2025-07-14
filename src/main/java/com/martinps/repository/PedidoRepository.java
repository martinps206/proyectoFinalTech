package com.martinps.repository;

import com.martinps.model.EstadoPedido;
import com.martinps.model.Pedido;
import com.martinps.model.Producto;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.lineas")
    List<Pedido> findAllWithLineas();

    List<Pedido> findByEstado(EstadoPedido estado);


}
