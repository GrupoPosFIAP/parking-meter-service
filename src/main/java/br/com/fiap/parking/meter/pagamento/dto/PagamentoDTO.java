package br.com.fiap.parking.meter.pagamento.dto;

import java.math.BigDecimal;

import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagamentoDto {
    
    private Long id;

    private EstacionamentoDTO estacionamento;

    private String formaPagamento;

    private BigDecimal valorPagamento;
}
