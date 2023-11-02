package br.com.fiap.parking.meter.pagamento.domain;

import java.math.BigDecimal;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import br.com.fiap.parking.meter.pagamento.FormaPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Entity(name = "tb_pagamento")
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Estacionamento estacionamento;

    private FormaPagamento formaPagamento;

    private BigDecimal valorPagamento;

    public Pagamento() {

    }
}
