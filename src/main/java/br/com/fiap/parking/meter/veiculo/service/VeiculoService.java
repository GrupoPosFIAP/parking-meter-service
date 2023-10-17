package br.com.fiap.parking.meter.veiculo.service;

import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoDto;
import br.com.fiap.parking.meter.veiculo.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;


    @Transactional(readOnly = true)
    public Page<VeiculoDto> findAll(int page, int size) {

        Page<Veiculo> result = this.veiculoRepository.findAll(PageRequest.of(page, size));
        return result.map(VeiculoDto::from);
    }


    @Transactional(readOnly = true)
    public Veiculo findById(Long id) {

        return this.veiculoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Veículo não encontrado.")
        );
    }


    @Transactional
    public VeiculoDto insert(VeiculoDto veiculoDto) {
        Veiculo veiculo = this.veiculoRepository.save(veiculoDto.toDomain());
        return VeiculoDto.from(veiculo);
    }


    @Transactional
    public void delete(Long id) {
        this.veiculoRepository.deleteById(id);
    }


    @Transactional
    public VeiculoDto update(Long id, VeiculoDto veiculoDto) {

        Veiculo veiculo = findById(id);
        veiculo.setPlaca(veiculoDto.placa());
        veiculo.setMarca(veiculoDto.marca());
        veiculo.setModelo(veiculoDto.modelo());
        this.veiculoRepository.save(veiculo);

        return veiculoDto;
    }
}
