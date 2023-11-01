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
    public ResponseEntity<String> iniciarEstacionamento(@RequestBody EstacionamentoDTO estacionamentoDTO) {
        String formaDePagamento = estacionamentoDTO.getFormaDePagamento();
        if (!"credito".equals(formaDePagamento) && !"debito".equals(formaDePagamento)
                && !"pix".equals(formaDePagamento)) {
            return ResponseEntity.badRequest().body("A forma de pagamento informada não é válida.");
        }

        boolean estacionamentoFixo = estacionamentoDTO.isEstacionamentoFixo();

        if ("pix".equals(formaDePagamento) && !estacionamentoFixo) {
            return ResponseEntity.badRequest()
                    .body("A opção PIX só está disponível para períodos de estacionamento fixos.");
        }

        LocalDateTime horarioInicio = LocalDateTime.now();
        estacionamentoDTO.setHorarioInicio(horarioInicio);

        return ResponseEntity.ok("Estacionamento iniciado com sucesso.");
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
