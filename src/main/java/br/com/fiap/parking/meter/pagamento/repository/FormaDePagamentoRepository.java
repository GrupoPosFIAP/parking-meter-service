package br.com.fiap.parking.meter.pagamento.repository;

import br.com.fiap.parking.meter.pagamento.domain.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {
}