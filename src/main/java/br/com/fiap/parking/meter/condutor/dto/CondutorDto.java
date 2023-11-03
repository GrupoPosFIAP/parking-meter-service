package br.com.fiap.parking.meter.condutor.dto;

import br.com.fiap.parking.meter.condutor.domain.Condutor;

public record CondutorDto(Long id, String nome, EnderecoDto endereco, String telefone, String email) {

    public static CondutorDto from(Condutor condutor) {
        return new CondutorDto(
                condutor.getId(),
                condutor.getNome(),
                EnderecoDto.from(condutor.getEndereco()),
                condutor.getTelefone(),
                condutor.getEmail()
        );
    }

    public Condutor toDomain() {
        return new Condutor(
                this.nome,
                this.endereco.toDomain(),
                this.telefone,
                this.email
        );
    }

    public static Condutor mapperDtoToEntity(CondutorDto dto, Condutor entity) {
        entity.setNome(dto.nome());
        // entity.setEndereco(dto.endereco());
        entity.setTelefone(dto.telefone());
        entity.setEmail(dto.email());
        return entity;
    }
}
