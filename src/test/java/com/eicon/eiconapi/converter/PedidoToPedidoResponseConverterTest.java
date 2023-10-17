package com.eicon.eiconapi.converter;

import com.eicon.eiconapi.domain.entity.Pedido;
import com.eicon.eiconapi.domain.response.PedidoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.eicon.eiconapi.fixture.FixtureFactory.validPedido;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
 class PedidoToPedidoResponseConverterTest {

 @Test
  void convert() {
   Pedido pedido = validPedido();
   PedidoResponse response = new PedidoToPedidoResponseConverter().convert(pedido);

   assertNotNull(response);
   assertEquals(pedido.getNumeroControle(), response.getNumeroControle());
   assertEquals(pedido.getDataCadastro(), response.getDataCadastro());
   assertEquals(pedido.getNome(), response.getNome());
   assertEquals(pedido.getValor(), response.getValor());
   assertEquals(pedido.getQuantidade(), response.getQuantidade());
   assertEquals(pedido.getValorTotal(), response.getValorTotal());
  }
}
