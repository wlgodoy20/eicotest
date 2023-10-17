package com.eicon.eiconapi.service;

import com.eicon.eiconapi.domain.entity.Pedido;
import com.eicon.eiconapi.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public List<Pedido> saveAll(List<Pedido> pedidos) {
        List<Pedido> pedidosSalvos = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
                log.info("Pedido com número de controle [{}] já cadastrado.", pedido.getNumeroControle());
            } else {
                pedidosSalvos.add(pedidoRepository.save(pedido));
            }
        }

        return pedidosSalvos;
    }

    public Pedido findByNumeroControle(String numControle) {
        return pedidoRepository.findByNumeroControle(numControle)
                .orElse(Pedido.builder().build());
    }

    public List<Pedido> findByParametros(LocalDate dataCadastro, String nome, Integer quantidade, Long valor, Long valorTotal) {
        return pedidoRepository.findByParametros(dataCadastro, nome, valor, quantidade, valorTotal);
    }

    public List<Pedido> consultaPedidosCliente(Long id) {
        return pedidoRepository.findAllByClienteId(id);
    }
}
