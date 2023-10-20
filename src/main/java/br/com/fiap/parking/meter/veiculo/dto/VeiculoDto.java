package br.com.fiap.parking.meter.veiculo.dto;

import br.com.fiap.parking.meter.veiculo.domain.Veiculo;

public record VeiculoDto(Long id, String placa, String marca, String modelo) {

    public static VeiculoDto from(Veiculo veiculo) {
        return new VeiculoDto(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo() );
    }


    public Veiculo toDomain() {
        return new Veiculo(
                this.placa,
                this.marca,
                this.modelo );
    }
}
