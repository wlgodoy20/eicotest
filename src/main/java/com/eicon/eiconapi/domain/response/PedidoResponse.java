package com.eicon.eiconapi.domain.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PedidoResponse {

    private String numeroControle;

    private LocalDate dataCadastro;

    private String nome;

    private Long valor;

    private Integer quantidade;

    private Long valorTotal;
}
