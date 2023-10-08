package br.com.fiap.parking.meter.pagamento.dto;

import br.com.fiap.parking.meter.carro.dto.CarroDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO {

    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private BigDecimal valor;
    private String formaDePagamento; // Pode ser "credito", "debito" ou "pix"
    private boolean estacionamentoFixo; // Indica se o estacionamento é fixo

    // Getters e Setters

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

    public boolean isEstacionamentoFixo() {
        return estacionamentoFixo;
    }

    public void setEstacionamentoFixo(boolean estacionamentoFixo) {
        this.estacionamentoFixo = estacionamentoFixo;
    }

}

