package br.com.fiap.parking.meter.veiculo.controller;

import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import br.com.fiap.parking.meter.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;


    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }


    @GetMapping
    public ResponseEntity<Page<VeiculoCondutorDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        var veiculos = veiculoService.findAll(pageRequest);
        return ResponseEntity.ok(veiculos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VeiculoCondutorDTO> findById(@PathVariable(name = "id") Long id) {
        var  veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(veiculo);
    }


    @PostMapping
    public ResponseEntity<VeiculoCondutorDTO> insert(@Valid @RequestBody VeiculoCondutorDTO dto) {
        var veiculo = this.veiculoService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((veiculo.id())).toUri();
        return ResponseEntity.created(uri).body(veiculo);
    }


    @PutMapping("/{id}")
    public ResponseEntity<VeiculoCondutorDTO> update(@PathVariable(name = "id") Long id, @RequestBody VeiculoCondutorDTO dto) {
        return ResponseEntity.ok(this.veiculoService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(name = "id") Long id) {
        this.veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
