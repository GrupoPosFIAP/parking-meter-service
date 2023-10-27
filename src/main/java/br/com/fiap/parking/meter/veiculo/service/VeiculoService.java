package br.com.fiap.parking.meter.veiculo.service;

import br.com.fiap.parking.meter.condutor.repository.CondutorRepository;
import br.com.fiap.parking.meter.exception.ControllerNotFoundException;
import br.com.fiap.parking.meter.exception.GenericException;
import br.com.fiap.parking.meter.veiculo.domain.Veiculo;
import br.com.fiap.parking.meter.veiculo.dto.VeiculoCondutorDTO;
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
        try {

            var condutor = condutorRepository.getReferenceById(dto.condutor().id());
            var entity = VeiculoCondutorDTO.toEntity(dto, condutor);
            var veiculoSaved = veiculoRepository.save(entity);
            return VeiculoCondutorDTO.fromEntity(veiculoSaved);

        } catch (GenericException e) {
            throw new ControllerNotFoundException("Condutor não encontrado.");
        }
    }


    @Transactional
    public VeiculoCondutorDTO update(Long id, VeiculoCondutorDTO dto) {

        try {
            Veiculo entity = veiculoRepository.getReferenceById(id);
            var condutor = condutorRepository.getReferenceById(dto.condutor().id());

            VeiculoCondutorDTO.mapperDtoToEntity(dto, entity, condutor);
            entity = veiculoRepository.save(entity);
            return VeiculoCondutorDTO.fromEntity(entity);

        }  catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor/Veículo não encontrados");
        }
    }


    @Transactional
    public void delete(Long id) {
        this.veiculoRepository.deleteById(id);
    }
}
