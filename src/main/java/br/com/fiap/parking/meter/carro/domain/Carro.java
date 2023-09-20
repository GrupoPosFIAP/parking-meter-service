package br.com.fiap.parking.meter.carro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity(name = "tb_carro")
public class Carro {

    @Id
    @NotBlank(message = "A placa do carro deve ser informada.")
    private String placa;

    @Column(name = "dt_entrada")
    @NotNull(message = "A data de entrada deve ser informada.")
    private LocalDateTime dataEntrada;

    @Column(name = "dt_saida")
    private LocalDateTime dataSaida;

    public Carro() {
    }

    public Carro(String placa, LocalDateTime dataEntrada, LocalDateTime dataSaida) {
        this.placa = placa;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public void updateDataSaida() {
        this.dataSaida = LocalDateTime.now();
    }

}
