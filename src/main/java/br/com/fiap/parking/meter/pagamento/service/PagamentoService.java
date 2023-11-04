package br.com.fiap.parking.meter.pagamento.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.estacionamento.repository.EstacionamentoRepository;
import br.com.fiap.parking.meter.pagamento.domain.Pagamento;
import br.com.fiap.parking.meter.pagamento.dto.PagamentoDto;
import br.com.fiap.parking.meter.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    private final EstacionamentoRepository estacionamentoRepository;

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(
            EstacionamentoRepository estacionamentoRepository,
            PagamentoRepository pagamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

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
