package br.com.fiap.parking.meter.condutor.dto;

import br.com.fiap.parking.meter.condutor.domain.Endereco;

public record EnderecoDto(String estado, String cidade, String bairro, String rua, Integer numero) {

    public static EnderecoDto from(Endereco endereco) {
        return new EnderecoDto(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getRua(),
                endereco.getNumero()
        );
    }

    public Endereco toDomain() {
        return new Endereco(
                this.estado,
                this.cidade,
                this.bairro,
                this.rua,
                this.numero
        );
    }
}
