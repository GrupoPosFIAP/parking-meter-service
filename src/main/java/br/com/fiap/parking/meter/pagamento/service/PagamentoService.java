package br.com.fiap.parking.meter.pagamento.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.parking.meter.estacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.pagamento.dto.PagamentoDto;
import br.com.fiap.parking.meter.pagamento.repository.PagamentoRepository;

@Service
public class PagamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDto payment(EstacionamentoDTO estacionamentoDto) {

        LocalDateTime horarioInicio = estacionamentoDto.getHorarioInicio();
        LocalDateTime horarioFim = estacionamentoDto.getHorarioFim();
        BigDecimal valor = estacionamentoDto.getValor();
        String formaDePagamento = estacionamentoDto.getFormaDePagamento();

        //Ajustar
//        var estacionamento = new Estacionamento(null, horarioInicio, horarioFim, valor, formaDePagamento);

        

        return null;
    }

}
