package com.eicon.eiconapi.converter;

import com.eicon.eiconapi.domain.dto.PedidoDto;
import com.eicon.eiconapi.domain.entity.Cliente;
import com.eicon.eiconapi.domain.entity.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PedidoDtoToPedidoConverter implements Converter<PedidoDto, Pedido> {

    @Override
    public Pedido convert(PedidoDto pedidoDto) {
        return Pedido.builder()
                .numeroControle(pedidoDto.getNumeroControle())
                .dataCadastro(pedidoDto.getDataCadastro() == null ? LocalDate.now() : pedidoDto.getDataCadastro())
                .nome(pedidoDto.getNome())
                .valor(pedidoDto.getValor())
                .quantidade(pedidoDto.getQuantidade() == 0 ? 1 : pedidoDto.getQuantidade())
                .cliente(Cliente.builder().id(pedidoDto.getCodigoCliente()).build())
                .valorTotal(getValorTotal(pedidoDto))
                .build();
    }

    private Long getValorTotal(PedidoDto pedidoDto) {
        var valorTotalParcial = pedidoDto.getValor()
                * (pedidoDto.getQuantidade() == 0 ? 1 : pedidoDto.getQuantidade());

        if (pedidoDto.getQuantidade() > 5 && pedidoDto.getQuantidade() < 10) {
            var desconto = valorTotalParcial * 0.05;
            return Math.round(valorTotalParcial - desconto);
        } else if (pedidoDto.getQuantidade() >= 10) {
            var desconto = valorTotalParcial * 0.10;
            return Math.round(valorTotalParcial - desconto);
        }

        return valorTotalParcial;
    }
}
