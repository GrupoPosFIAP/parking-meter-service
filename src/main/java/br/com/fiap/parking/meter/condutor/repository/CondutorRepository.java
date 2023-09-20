package br.com.fiap.parking.meter.condutor.repository;

import br.com.fiap.parking.meter.condutor.domain.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondutorRepository extends JpaRepository<Condutor, Long> {
}
