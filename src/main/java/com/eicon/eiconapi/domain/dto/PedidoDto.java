package com.eicon.eiconapi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
public class PedidoDto {

    @NotNull
    private String numeroControle;

    private LocalDate dataCadastro;

    @NotNull
    private String nome;

    @NotNull
    private Long valor;

    private int quantidade;

    @NotNull
    private Long codigoCliente;
}
