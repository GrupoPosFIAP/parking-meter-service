package br.com.fiap.parking.meter.pagamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.estacionamento.repository.EstacionamentoRepository;
import br.com.fiap.parking.meter.pagamento.domain.Pagamento;
import br.com.fiap.parking.meter.pagamento.dto.PagamentoDto;
import br.com.fiap.parking.meter.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDto payment(Long id) {

        Optional<Estacionamento> optional = estacionamentoRepository.findById(id);

        var estacionamento = optional.get();
        var formaPagamento = estacionamento.getFormaDePagamento();
        var valor = estacionamento.getValor();

        var pagamento = Pagamento
            .builder()
            .estacionamento(estacionamento)
            .formaPagamento(formaPagamento)
            .valorPagamento(valor)
            .build();

        var saved = pagamentoRepository.save(pagamento);

        var estacionamentoDto = EstacionamentoDTO
            .builder()
            .estacionamentoFixo(true)
            .tempoRealUtilizado(true)
            .formaDePagamento(saved.getFormaPagamento())
            .horarioInicio(saved.getEstacionamento().getHorarioInicio())
            .horarioFim(saved.getEstacionamento().getHorarioFim())
            .build();

        var pagamentoDto = new PagamentoDto(
            saved.getId(), 
            estacionamentoDto, 
            saved.getFormaPagamento(), 
            saved.getValorPagamento());

        return pagamentoDto;
    }

}
