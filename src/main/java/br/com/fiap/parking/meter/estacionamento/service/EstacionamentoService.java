package br.com.fiap.parking.meter.estacionamento.service;

import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.estacionamento.repository.EstacionamentoRepository;
import br.com.fiap.parking.meter.exception.ControllerNotFoundException;
import br.com.fiap.parking.meter.exception.EntityNotFoundException;
import br.com.fiap.parking.meter.exception.GenericException;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoDto;
import br.com.fiap.parking.meter.veiculo.repository.VeiculoRepository;
import br.com.fiap.parking.meter.veiculo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;


    public void validarEstacionamento(EstacionamentoDTO estacionamentoDTO){
        String formaDePagamento = estacionamentoDTO.getFormaDePagamento();
        if (!"credito".equals(formaDePagamento) && !"debito".equals(formaDePagamento)
                && !"pix".equals(formaDePagamento)) {
            throw new GenericException("A forma de pagamento informada não é válida.");
        }

        boolean estacionamentoFixo = estacionamentoDTO.isEstacionamentoFixo();

        if ("pix".equals(formaDePagamento) && !estacionamentoFixo) {
            throw new GenericException("A opção PIX só está disponível para períodos de estacionamento fixos.");

        }
    }

    public Estacionamento calcularPagamento(LocalDateTime horarioInicio, LocalDateTime horarioFim,
            String formaDePagamento, boolean estacionamentoFixo,
            boolean tempoRealUtilizado) {
        BigDecimal valorCalculado = calcularValor(horarioInicio, horarioFim, estacionamentoFixo, tempoRealUtilizado);

        Estacionamento pagamento = new Estacionamento();
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

    public void iniciarEstacionamento(EstacionamentoDTO estacionamentoDTO){
        var veiculoId = estacionamentoDTO.getVeiculoId();
        var veiculo = veiculoRepository.findById(veiculoId).orElseThrow(
                () -> new EntityNotFoundException("Veículo não encontrado.")
        );
        var entity = EstacionamentoDTO.toEntity(estacionamentoDTO, veiculo);
        var estacionamentoSaved = estacionamentoRepository.save(entity);
    }

    public List<Estacionamento> findAll() {
        return estacionamentoRepository.findAll();
    }

    public Estacionamento findById(Long id) {
        return estacionamentoRepository.findById(id).orElse(null);
    }

    public Estacionamento insert(Estacionamento estacionamento) {
        return estacionamentoRepository.save(estacionamento);
    }

    public Estacionamento update(Long id, Estacionamento estacionamento) {
        if (estacionamentoRepository.existsById(id)) {
            estacionamento.setId(id);
            return estacionamentoRepository.save(estacionamento);
        }
        return null;
    }

    public void delete(Long id) {
        estacionamentoRepository.deleteById(id);
    }
}