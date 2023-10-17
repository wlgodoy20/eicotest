package com.eicon.eiconapi.controller;

import com.eicon.eiconapi.domain.dto.PedidoDto;
import com.eicon.eiconapi.domain.entity.Pedido;
import com.eicon.eiconapi.domain.response.PedidoResponse;
import com.eicon.eiconapi.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/pedidos")
public class PedidoController {

    private final ConversionService conversionService;
    private final PedidoService pedidoService;

    @PostMapping(value= "/", consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PedidoResponse>> create(@Valid @Size(min=1, max=10) @RequestBody List<@Valid PedidoDto> pedidoDto) {
        var pedidosSalvos = pedidoService.saveAll(convertRequest(pedidoDto));
        var response = this.convertResponse(pedidosSalvos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value= "/consulta")
    public ResponseEntity<List<Pedido>> consultaParametro(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false, name = "data") LocalDate dataCadastro,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer quantidade,
            @RequestParam(required = false) Long valor,
            @RequestParam(required = false) Long valorTotal) {
        var pedidos = pedidoService.findByParametros(dataCadastro, nome, quantidade, valor, valorTotal);
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }

    @GetMapping(value= "/consulta/{numeroControle}")
    public ResponseEntity<Pedido> consultaPorNumeroControle(@PathVariable String numeroControle) {
        var pedido = pedidoService.findByNumeroControle(numeroControle);
        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @GetMapping(value= "/cliente/{id}")
    public ResponseEntity<List<Pedido>> consultaPedidosCliente(@PathVariable Long id) {
        var pedidos = pedidoService.consultaPedidosCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidos);
    }


    @SuppressWarnings("unchecked")
    private List<Pedido> convertRequest(List<PedidoDto> pedidoDtos) {
        var sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(PedidoDto.class));
        var targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Pedido.class));
        return (List<Pedido>) conversionService.convert(pedidoDtos, sourceType, targetType);
    }

    @SuppressWarnings("unchecked")
    private List<PedidoResponse> convertResponse(List<Pedido> pedidos) {
        var sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Pedido.class));
        var targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(PedidoResponse.class));
        return (List<PedidoResponse>) conversionService.convert(pedidos, sourceType, targetType);
    }
}
