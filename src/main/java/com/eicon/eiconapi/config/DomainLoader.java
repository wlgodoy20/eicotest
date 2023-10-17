package com.eicon.eiconapi.config;

import com.eicon.eiconapi.domain.entity.Cliente;
import com.eicon.eiconapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class DomainLoader {

    private final ClienteRepository clienteRepository;

    @PostConstruct
    public void createBins() {
        this.createCliente(1L, "Cliente1");
        this.createCliente(2L, "Cliente2");
        this.createCliente(3L, "Cliente3");
        this.createCliente(4L, "Cliente4");
        this.createCliente(5L, "Cliente5");
        this.createCliente(6L, "Cliente6");
        this.createCliente(7L, "Cliente7");
        this.createCliente(8L, "Cliente8");
        this.createCliente(9L, "Cliente9");
        this.createCliente(10L, "Cliente10");
    }

    private void createCliente(Long id, String nome) {
        var cliente = Cliente.builder()
                .id(id)
                .nome(nome)
                .build();

        clienteRepository.save(cliente);
    }
}
