package br.com.fiap.parking.meter.pagamento.dto;

import java.math.BigDecimal;

import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.pagamento.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagamentoDto {
    
    private Long id;

    private EstacionamentoDTO estacionamento;

    private FormaPagamento formaPagamento;

    private BigDecimal valorPagamento;
}
