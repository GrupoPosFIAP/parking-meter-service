package br.com.fiap.parking.meter.veiculo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.parking.meter.condutor.repository.CondutorRepository;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
import br.com.fiap.parking.meter.veiculo.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;
    private final CondutorRepository condutorRepository;


    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository, CondutorRepository condutorRepository) {
        this.veiculoRepository = veiculoRepository;
        this.condutorRepository = condutorRepository;
    }


    @Transactional(readOnly = true)
    public Page<VeiculoCondutorDTO> findAll(PageRequest pageRequest) {

        var veiculos = veiculoRepository.findAll(pageRequest);
        return veiculos.map(VeiculoCondutorDTO::fromEntity);

    }


    @Transactional(readOnly = true)
    public VeiculoCondutorDTO findById(Long id) {
        var veiculo = veiculoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Veículo não encontrado.")
        );
        return VeiculoCondutorDTO.fromEntity(veiculo);
    }


    @Transactional
    public VeiculoCondutorDTO insert(VeiculoCondutorDTO dto) {

        var condutor = condutorRepository.findById(dto.condutorId()).orElseThrow(
                () -> new EntityNotFoundException("Condutor não encontrado")
        );
        var entity = VeiculoCondutorDTO.toEntity(dto, condutor);
        var veiculoSaved = veiculoRepository.save(entity);
        return VeiculoCondutorDTO.fromEntity(veiculoSaved);
    }


    @Transactional
    public VeiculoCondutorDTO update(Long id, VeiculoCondutorDTO dto) {

        Veiculo entity = veiculoRepository.getReferenceById(id);
        var condutor = condutorRepository.findById(dto.condutorId()).orElseThrow(
                () -> new EntityNotFoundException("Condutor não encontrado")
        );

        VeiculoCondutorDTO.mapperDtoToEntity(dto, entity, condutor);
        entity = veiculoRepository.save(entity);
        return VeiculoCondutorDTO.fromEntity(entity);
    }


    @Transactional
    public void delete(Long id) {
        this.veiculoRepository.deleteById(id);
    }
}
