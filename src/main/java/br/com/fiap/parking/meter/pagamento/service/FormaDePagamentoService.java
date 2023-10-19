package br.com.fiap.parking.meter.pagamento.service;

import br.com.fiap.parking.meter.pagamento.domain.FormaDePagamento;
import br.com.fiap.parking.meter.pagamento.repository.FormaDePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormaDePagamentoService {

    @Autowired
    private FormaDePagamentoRepository formaDePagamentoRepository;

    public FormaDePagamento obterFormaDePagamento(Long id) {
        return this.formaDePagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada"));
    }

    // Outros métodos relacionados à gestão de formas de pagamento

}