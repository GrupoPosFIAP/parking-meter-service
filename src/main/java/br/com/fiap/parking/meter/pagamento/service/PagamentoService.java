package br.com.fiap.parking.meter.pagamento.service;

import br.com.fiap.parking.meter.pagamento.domain.Pagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service

public class PagamentoService {

    public Pagamento calcularPagamento(LocalDateTime horarioInicio, LocalDateTime horarioFim, boolean estacionamentoFixo) {
        BigDecimal valorCalculado = calcularValor(horarioInicio, horarioFim, estacionamentoFixo);

        Pagamento pagamento = new Pagamento();
        pagamento.setHorarioInicio(horarioInicio);
        pagamento.setHorarioFim(horarioFim);
        pagamento.setValor(valorCalculado);

        return pagamento;
    }

    private BigDecimal calcularValor(LocalDateTime horarioInicio, LocalDateTime horarioFim, boolean estacionamentoFixo) {
        // Defina a tarifa base por hora (ou outra unidade, dependendo da sua lógica)
        BigDecimal tarifaBasePorHora = BigDecimal.valueOf(5);

        // Calcule a duração do estacionamento
        Duration duracao = Duration.between(horarioInicio, horarioFim);

        // Verifique se o estacionamento é fixo
        if (estacionamentoFixo) {
            // Para estacionamento fixo, você pode aplicar uma tarifa específica
            BigDecimal tarifaFixa = BigDecimal.valueOf(20); // Por exemplo, R$ 20 para estacionamento fixo
            return tarifaFixa;
        } else {
            // Para estacionamento variável, calcule com base na duração
            long horasEstacionado = duracao.toHours();
            return tarifaBasePorHora.multiply(BigDecimal.valueOf(horasEstacionado));
        }
    }
}