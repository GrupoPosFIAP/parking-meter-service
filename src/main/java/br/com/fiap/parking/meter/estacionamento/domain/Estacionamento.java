package br.com.fiap.parking.meter.estacionamento.domain;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_estacionamento")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private BigDecimal valor;

    @Column(name = "forma_de_pagamento")
    private String formaDePagamento; // Referência à forma de pagamento

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public Estacionamento(EstacionamentoDTO dto, Veiculo veiculo) {
        this.horarioInicio = dto.getHorarioInicio();
        this.horarioFim = dto.getHorarioFim();
        this.valor = dto.getValor();
        this.formaDePagamento = dto.getFormaDePagamento();
        this.veiculo = veiculo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDateTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
}
