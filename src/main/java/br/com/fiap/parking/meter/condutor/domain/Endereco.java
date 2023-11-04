package br.com.fiap.parking.meter.condutor.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O estado não pode ser vazio.")
    @Length(min = 2, max = 2, message = "O estado deve conter 2 caracteres.")
    private String estado;

    @NotEmpty(message = "A cidade não pode ser vazia.")
    private String cidade;

    @NotEmpty(message = "O bairro não pode ser vazio.")
    private String bairro;

    @NotEmpty(message = "A rua não pode ser vazia.")
    private String rua;

    private Integer numero;

    public Endereco() {
    }

    public Endereco(String estado, String cidade, String bairro, String rua, Integer numero) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
