package com.eicon.eiconapi.repository;

import com.eicon.eiconapi.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE (:dataCadastro is null or p.dataCadastro = :dataCadastro) "
            + "AND (:nome is null or p.nome = :nome) "
            + "AND (:valor is null or p.valor = :valor) "
            + "AND (:quantidade is null or p.quantidade = :quantidade) "
            + "AND (:valorTotal is null or p.valorTotal = :valorTotal)")
    List<Pedido> findByParametros(
            @Param("dataCadastro") LocalDate dataCadastro,
            @Param("nome") String nome,
            @Param("valor") Long valor,
            @Param("quantidade") Integer quantidade,
            @Param("valorTotal") Long valorTotal);

    boolean existsByNumeroControle(String numControle);

    Optional<Pedido> findByNumeroControle(String numControle);

    List<Pedido> findAllByClienteId(Long id);
}
