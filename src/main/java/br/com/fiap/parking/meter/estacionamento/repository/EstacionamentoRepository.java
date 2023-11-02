package br.com.fiap.parking.meter.estacionamento.repository;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}