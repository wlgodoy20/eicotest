package com.eicon.eiconapi.converter;

import com.eicon.eiconapi.domain.dto.PedidoDto;
import com.eicon.eiconapi.domain.entity.Pedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.eicon.eiconapi.fixture.FixtureFactory.validPedidoDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PedidoDtoToPedidoConverterTest {

    @Test
    void convert() {
        PedidoDto dto = validPedidoDto();
        Pedido pedido = new PedidoDtoToPedidoConverter().convert(dto);

        assertNotNull(pedido);
        assertEquals(dto.getNumeroControle(), pedido.getNumeroControle());
        assertEquals(dto.getDataCadastro(), pedido.getDataCadastro());
        assertEquals(dto.getNome(), pedido.getNome());
        assertEquals(dto.getValor(), pedido.getValor());
        assertEquals(dto.getQuantidade(), pedido.getQuantidade());
        assertEquals(dto.getCodigoCliente(), pedido.getCliente().getId());
        assertEquals(9000L, pedido.getValorTotal());
    }

    @Test
    void convertValorTotalDesconto5() {
        PedidoDto dto = validPedidoDto();
        dto.setQuantidade(6);
        Pedido pedido = new PedidoDtoToPedidoConverter().convert(dto);

        assertEquals(5700L, pedido.getValorTotal());
    }
}
