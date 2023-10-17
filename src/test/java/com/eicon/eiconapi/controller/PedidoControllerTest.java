package com.eicon.eiconapi.controller;

import com.eicon.eiconapi.domain.dto.PedidoDto;
import com.eicon.eiconapi.domain.entity.Pedido;
import com.eicon.eiconapi.domain.response.PedidoResponse;
import com.eicon.eiconapi.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.eicon.eiconapi.fixture.FixtureFactory.validPedido;
import static com.eicon.eiconapi.fixture.FixtureFactory.validPedidoDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock
    private ConversionService conversionService;

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController controller;

    @Test
    void create() {
        PedidoDto dto = validPedidoDto();
        List<PedidoDto> dtoList = new ArrayList<>();
        dtoList.add(dto);

        Pedido pedido = validPedido();
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(pedido);

        when(conversionService.convert(any(), any(), any())).thenReturn(pedidoList);
        when(pedidoService.saveAll(pedidoList)).thenReturn(pedidoList);

        ResponseEntity<List<PedidoResponse>> response = controller.create(dtoList);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(pedidoService).saveAll(pedidoList);
    }

    @Test
    void consultaPorParametros() {
        LocalDate dataCadastro = LocalDate.now();
        String nome = "Produto";
        Integer quantidade = 10;
        Long valor = 1000L;
        Long valorTotal = 1000L;

        Pedido pedido = validPedido();
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(pedido);

        when(pedidoService.findByParametros(dataCadastro, nome, quantidade, valor, valorTotal)).thenReturn(pedidoList);

        ResponseEntity<List<Pedido>> response = controller.consultaParametro(dataCadastro, nome, quantidade, valor, valorTotal);

        assertNotNull(response.getBody());
        verify(pedidoService).findByParametros(dataCadastro, nome, quantidade, valor, valorTotal);
    }

    @Test
    void consultaPorNumeroControle() {
        String numeroControle = "1977";

        Pedido pedido = validPedido();

        when(pedidoService.findByNumeroControle(numeroControle)).thenReturn(pedido);

        ResponseEntity<Pedido> response = controller.consultaPorNumeroControle(numeroControle);

        assertNotNull(response.getBody());
        verify(pedidoService).findByNumeroControle(numeroControle);
    }

    @Test
    void consultaPedidosCliente() {
        Long cliente = 1977L;

        Pedido pedido = validPedido();
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(pedido);

        when(pedidoService.consultaPedidosCliente(cliente)).thenReturn(pedidoList);

        ResponseEntity<List<Pedido>> response = controller.consultaPedidosCliente(cliente);

        assertNotNull(response.getBody());
        verify(pedidoService).consultaPedidosCliente(cliente);
    }
}
