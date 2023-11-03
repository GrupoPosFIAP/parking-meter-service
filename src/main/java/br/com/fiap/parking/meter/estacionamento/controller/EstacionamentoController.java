package br.com.fiap.parking.meter.estacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.parking.meter.estacionamento.domain.Estacionamento;
import br.com.fiap.parking.meter.estacionamento.dto.EstacionamentoDTO;
import br.com.fiap.parking.meter.estacionamento.service.EstacionamentoService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @PostMapping("/iniciar")
    public ResponseEntity<Void> iniciarEstacionamento(@RequestBody EstacionamentoDTO estacionamentoDTO) {
        estacionamentoService.validarEstacionamento(estacionamentoDTO);
        LocalDateTime horarioInicio = LocalDateTime.now();
        estacionamentoDTO.setHorarioInicio(horarioInicio);
        estacionamentoService.iniciarEstacionamento(estacionamentoDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calcular-pagamento")
    public ResponseEntity<Estacionamento> calcularPagamento(@RequestParam LocalDateTime horarioInicio,
                                                            @RequestParam LocalDateTime horarioFim,
                                                            @RequestParam String formaDePagamento,
                                                            @RequestParam boolean estacionamentoFixo,
                                                            @RequestParam boolean tempoRealUtilizado) {
        Estacionamento estacionamento = estacionamentoService.calcularPagamento(horarioInicio, horarioFim, formaDePagamento,
                estacionamentoFixo, tempoRealUtilizado);
        return ResponseEntity.ok(estacionamento);
    }
}
