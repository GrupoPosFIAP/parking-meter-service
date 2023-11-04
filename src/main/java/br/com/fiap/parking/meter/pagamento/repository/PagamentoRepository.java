package br.com.fiap.parking.meter.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.parking.meter.pagamento.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
