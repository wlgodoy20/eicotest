package com.eicon.eiconapi.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@SuperBuilder
@Table(schema = "pedido")
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numeroControle;

    private LocalDate dataCadastro;

    private String nome;

    private Long valor;

    private Integer quantidade;

    private Long valorTotal;

    @ManyToOne
    @JoinColumn(name = "codCliente")
    private Cliente cliente;
}
