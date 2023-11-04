package br.com.fiap.parking.meter.condutor.domain;

import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity(name = "tb_condutor")
public class Condutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome do condutor é obrigatório.")
    private String nome;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @NotEmpty(message = "Telefone do condutor é obrigatório.")
    private String telefone;

    @NotEmpty(message = "E-mail do condutor é obrigatório.")
    @Email(message = "O e-mail deve ser informado em um formato válido.")
    private String email;

    @OneToMany(mappedBy = "condutor")
    private List<Veiculo> veiculos;

    public Condutor() {
    }

    public Condutor(String nome, Endereco endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
