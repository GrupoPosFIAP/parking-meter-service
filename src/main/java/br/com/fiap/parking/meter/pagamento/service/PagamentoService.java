package br.com.fiap.parking.meter.pagamento.service;

import br.com.fiap.parking.meter.pagamento.domain.Pagamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service

public class PagamentoService {

    public Pagamento calcularPagamento(LocalDateTime horarioInicio, LocalDateTime horarioFim,
                                       String formaDePagamento, boolean estacionamentoFixo,
                                       boolean tempoRealUtilizado) {
        BigDecimal valorCalculado = calcularValor(horarioInicio, horarioFim, estacionamentoFixo, tempoRealUtilizado);

        Pagamento pagamento = new Pagamento();
        pagamento.setHorarioInicio(horarioInicio);
        pagamento.setHorarioFim(horarioFim);
        pagamento.setValor(valorCalculado);
        pagamento.setFormaDePagamento(formaDePagamento);

        return pagamento;
    }

    private BigDecimal calcularValor(LocalDateTime horarioInicio, LocalDateTime horarioFim,
                                     boolean estacionamentoFixo, boolean tempoRealUtilizado) {
        // Defina a tarifa base por hora (ou outra unidade, dependendo da sua lógica)
        BigDecimal tarifaBasePorHora = BigDecimal.valueOf(5);

        // Calcule a duração do estacionamento
        Duration duracao = Duration.between(horarioInicio, horarioFim);

        // Verifique se o estacionamento é fixo
        if (estacionamentoFixo) {
            // Para estacionamento fixo, o valor é calculado com base no tempo fixo
            return tarifaBasePorHora;
        } else {
            // Para estacionamento variável, calcule com base na duração
            long horasEstacionado = duracao.toHours();

            if (tempoRealUtilizado) {
                // Se o tempo real foi utilizado, cobre por hora completa
                return tarifaBasePorHora.multiply(BigDecimal.valueOf(horasEstacionado));
            } else {
                // Se o tempo real não foi utilizado, cobre um valor fixo
                return tarifaBasePorHora;
            }
        }
    }
}