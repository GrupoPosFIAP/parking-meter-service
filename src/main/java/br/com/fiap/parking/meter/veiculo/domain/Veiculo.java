package br.com.fiap.parking.meter.veiculo.domain;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "tb_veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A placa do veículo deve ser informada.")
    private String placa;

    @NotBlank(message = "A marca do veículo deve ser informada.")
    private String marca;

    @NotBlank(message = "O modelo do veículo deve ser informado.")
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "condutor_id", nullable = false)
    private Condutor condutor;

    public Veiculo() {
    }

    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Veiculo (VeiculoCondutorDTO dto, Condutor condutor) {
        this.id = dto.id();
        this.marca = dto.marca();
        this.modelo = dto.modelo();
        this.placa = dto.placa();
        this.condutor = condutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
}
