package com.eicon.eiconapi.fixture;

import com.eicon.eiconapi.domain.dto.PedidoDto;
import com.eicon.eiconapi.domain.entity.Cliente;
import com.eicon.eiconapi.domain.entity.Pedido;

import java.time.LocalDate;

public class FixtureFactory {
    public static PedidoDto validPedidoDto() {
        return PedidoDto.builder()
                .numeroControle("1000")
                .dataCadastro(LocalDate.now())
                .nome("Produto")
                .valor(1000L)
                .quantidade(10)
                .codigoCliente(1L)
                .build();
    }

    public static Pedido validPedido() {
        return Pedido.builder()
                .numeroControle("1000")
                .dataCadastro(LocalDate.now())
                .nome("Produto")
                .valor(1000L)
                .quantidade(1)
                .cliente(Cliente.builder()
                        .id(1L)
                        .nome("Cliente1")
                        .build())
                .valorTotal(1000L)
                .build();
    }
}
