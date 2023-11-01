package br.com.fiap.parking.meter.estacionamento.dto;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EstacionamentoDTO {

    private long veiculoId;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private BigDecimal valor;
    private String formaDePagamento; // Pode ser "credito", "debito" ou "pix"
    private boolean estacionamentoFixo; // Indica se o estacionamento é fixo
    private boolean tempoRealUtilizado; // Indica se o tempo real foi utilizado

    // Getters e Setters
    public long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(long veiculoId) {
        this.veiculoId = veiculoId;
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

    public boolean isEstacionamentoFixo() {
        return estacionamentoFixo;
    }

    public void setEstacionamentoFixo(boolean estacionamentoFixo) {
        this.estacionamentoFixo = estacionamentoFixo;
    }

    public boolean isTempoRealUtilizado() {
        return tempoRealUtilizado;
    }

    public void setTempoRealUtilizado(boolean tempoRealUtilizado) {
        this.tempoRealUtilizado = tempoRealUtilizado;
    }

    public static Estacionamento toEntity(EstacionamentoDTO dto, Veiculo veiculo) {
        return new Estacionamento(dto, veiculo);
    }

}
