package br.com.fiap.parking.meter.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
