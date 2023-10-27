package br.com.fiap.parking.meter.veiculo.dto;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.condutor.dto.CondutorDto;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import jakarta.validation.constraints.NotBlank;

public record VeiculoCondutorDTO(
        Long id,

        @NotBlank(message = "Informe a placa do veículo")
        String placa,

        @NotBlank(message = "Informe a marca do veículo")
        String marca,

        @NotBlank(message = "Informe o moodelo do veículo")
        String modelo,

        Long condutorId ) {

    public static Veiculo toEntity(VeiculoCondutorDTO dto, Condutor condutor) {
        return new Veiculo(dto, condutor);
    }

    public static  VeiculoCondutorDTO fromEntity(Veiculo veiculo) {
        return new VeiculoCondutorDTO(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getCondutor().getId() );
    }

    public static Veiculo mapperDtoToEntity(VeiculoCondutorDTO dto, Veiculo entity, Condutor condutor) {
        entity.setPlaca(dto.placa());
        entity.setMarca(dto.marca());
        entity.setModelo(dto.modelo());
        entity.setCondutor(condutor);
        return entity;
    }
}
