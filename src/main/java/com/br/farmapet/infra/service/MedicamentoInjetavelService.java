package com.br.farmapet.infra.service;

import com.br.farmapet.domain.dto.request.CreateMedicamentoInjetavelDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoInjetavelDTO;
import com.br.farmapet.domain.model.Medicamento;
import com.br.farmapet.domain.model.MedicamentoInjetavel;
import com.br.farmapet.infra.repository.MedicamentoInjetavelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicamentoInjetavelService {
    private final MedicamentoInjetavelRepository medicamentoInjetavelRepository;

    @Autowired
    public MedicamentoInjetavelService(MedicamentoInjetavelRepository medicamentoInjetavelRepository) {
        this.medicamentoInjetavelRepository = medicamentoInjetavelRepository;
    }


    public CreateMedicamentoInjetavelDTO cadastrarMedicamentoInjetavel(CreateMedicamentoInjetavelDTO createMedicamentoInjetavelDTO) {
        MedicamentoInjetavel medicamentoInjetavel = new MedicamentoInjetavel();
        BeanUtils.copyProperties(createMedicamentoInjetavelDTO, medicamentoInjetavel);
        MedicamentoInjetavel novoMedicamentoInjetavel = medicamentoInjetavelRepository.save(medicamentoInjetavel);
        CreateMedicamentoInjetavelDTO novoCreateMedicamentoInjetavelDTO = new CreateMedicamentoInjetavelDTO();
        BeanUtils.copyProperties(novoMedicamentoInjetavel, novoCreateMedicamentoInjetavelDTO);
        return novoCreateMedicamentoInjetavelDTO;
    }

    public List<CreateMedicamentoInjetavelDTO> cadastrarListaMedicamentosInjetaveis(List<CreateMedicamentoInjetavelDTO> medicamentosInjetaveisDTO) {
        List<CreateMedicamentoInjetavelDTO> novosMedicamentosInjetaveisDTO = new ArrayList<>();

        for (CreateMedicamentoInjetavelDTO createMedicamentoInjetavelDTO : medicamentosInjetaveisDTO) {
            if (StringUtils.isBlank(createMedicamentoInjetavelDTO.getTipoAplicacao())) {
                throw new IllegalArgumentException("O campo 'tipoAplicacao' é obrigatório.");
            }

            MedicamentoInjetavel medicamentoInjetavel = new MedicamentoInjetavel();
            BeanUtils.copyProperties(createMedicamentoInjetavelDTO, medicamentoInjetavel);
            medicamentoInjetavel.setTipoAplicacao(createMedicamentoInjetavelDTO.getTipoAplicacao().toUpperCase());
            MedicamentoInjetavel novoMedicamentoInjetavel = medicamentoInjetavelRepository.save(medicamentoInjetavel);
            CreateMedicamentoInjetavelDTO novoCreateMedicamentoInjetavelDTO = new CreateMedicamentoInjetavelDTO();
            BeanUtils.copyProperties(novoMedicamentoInjetavel, novoCreateMedicamentoInjetavelDTO);
            novosMedicamentosInjetaveisDTO.add(novoCreateMedicamentoInjetavelDTO);
        }

        return novosMedicamentosInjetaveisDTO;
    }


    public List<GetMedicamentoInjetavelDTO> buscarMedicamentosInjetaveis() {
        List<Medicamento> medicamentosInjetaveis = medicamentoInjetavelRepository.findAll();
        List<GetMedicamentoInjetavelDTO> medicamentosInjetaveisDTO = new ArrayList<>();

        for (Medicamento medicamentoInjetavel : medicamentosInjetaveis) {
            GetMedicamentoInjetavelDTO medicamentoInjetavelDTO = new GetMedicamentoInjetavelDTO();
            BeanUtils.copyProperties(medicamentoInjetavel, medicamentoInjetavelDTO);
            medicamentosInjetaveisDTO.add(medicamentoInjetavelDTO);
        }

        return medicamentosInjetaveisDTO;
    }






}
