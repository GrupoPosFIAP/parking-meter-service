package br.com.fiap.parking.meter.veiculo.repository;

import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long  > {
}
