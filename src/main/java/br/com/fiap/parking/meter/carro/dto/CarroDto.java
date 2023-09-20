package br.com.fiap.parking.meter.carro.dto;

import br.com.fiap.parking.meter.carro.domain.Carro;

import java.time.LocalDateTime;

public record CarroDto(
        String placa,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida
) {

    public Carro toDomain() {
        return new Carro(this.placa, this.dataEntrada, this.dataSaida);
    }

    public static CarroDto toDto(Carro carro) {
        return new CarroDto(carro.getPlaca(), carro.getDataEntrada(), carro.getDataSaida());
    }

}
