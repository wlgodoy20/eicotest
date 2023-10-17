package com.eicon.eiconapi.converter;

import com.eicon.eiconapi.domain.entity.Pedido;
import com.eicon.eiconapi.domain.response.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoToPedidoResponseConverter implements Converter<Pedido, PedidoResponse> {

    @Override
    public PedidoResponse convert(Pedido pedido) {
        return PedidoResponse.builder()
                .numeroControle(pedido.getNumeroControle())
                .dataCadastro(pedido.getDataCadastro())
                .nome(pedido.getNome())
                .valor(pedido.getValor())
                .quantidade(pedido.getQuantidade())
                .valorTotal(pedido.getValorTotal())
                .build();
    }
}
