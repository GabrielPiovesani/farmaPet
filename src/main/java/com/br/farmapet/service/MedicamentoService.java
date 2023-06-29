package com.br.farmapet.service;

import com.br.farmapet.domain.Medicamento;
import com.br.farmapet.dto.CreateMedicamentoDTO;
import com.br.farmapet.repository.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public CreateMedicamentoDTO cadastrarMedicamento(CreateMedicamentoDTO createMedicamentoDTO) {
        Medicamento medicamento = new Medicamento();
        BeanUtils.copyProperties(createMedicamentoDTO, medicamento);
        Medicamento novoMedicamento = medicamentoRepository.save(medicamento);
        CreateMedicamentoDTO novoCreateMedicamentoDTO = new CreateMedicamentoDTO();
        BeanUtils.copyProperties(novoMedicamento, novoCreateMedicamentoDTO);
        return novoCreateMedicamentoDTO;
    }

    public CreateMedicamentoDTO atualizarMedicamento(Long id, CreateMedicamentoDTO createMedicamentoDTO) {
        Optional<Medicamento> optionalMedicamento = medicamentoRepository.findById(id);
        if (optionalMedicamento.isPresent()) {
            Medicamento medicamento = optionalMedicamento.get();
            BeanUtils.copyProperties(createMedicamentoDTO, medicamento);
            Medicamento medicamentoAtualizado = medicamentoRepository.save(medicamento);
            CreateMedicamentoDTO medicamentoAtualizadoDTO = new CreateMedicamentoDTO();
            BeanUtils.copyProperties(medicamentoAtualizado, medicamentoAtualizadoDTO);
            return medicamentoAtualizadoDTO;
        } else {
            throw new ResourceNotFoundException("Medicamento não encontrado com o ID: " + id);
        }
    }

    public void deletarMedicamento(Long id) {
        if (medicamentoRepository.existsById(id)) {
            medicamentoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Medicamento não encontrado com o ID: " + id);
        }
    }


    public List<CreateMedicamentoDTO> listarMedicamentos() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        return CreateMedicamentoDTO.convertToDTOList(medicamentos);
    }

    public List<CreateMedicamentoDTO> filtrarMedicamentos(String marca, String fabricante, Double precoMenorQue, Double precoMaiorQue) {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        List<Medicamento> medicamentosFiltrados = medicamentos.stream()
                .filter(medicamento -> marca == null || medicamento.getMarca().equalsIgnoreCase(marca))
                .filter(medicamento -> fabricante == null || medicamento.getFabricante().equalsIgnoreCase(fabricante))
                .filter(medicamento -> precoMenorQue == null || medicamento.getPreco() < precoMenorQue)
                .filter(medicamento -> precoMaiorQue == null || medicamento.getPreco() > precoMaiorQue)
                .collect(Collectors.toList());

        return CreateMedicamentoDTO.convertToDTOList(medicamentosFiltrados);
    }

    public List<CreateMedicamentoDTO> ordenarMedicamentos(String ordem) {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        if (ordem.equalsIgnoreCase("asc")) {
            medicamentos.sort((m1, m2) -> Double.compare(m1.getPreco(), m2.getPreco()));
        } else if (ordem.equalsIgnoreCase("desc")) {
            medicamentos.sort((m1, m2) -> Double.compare(m2.getPreco(), m1.getPreco()));
        }

        return CreateMedicamentoDTO.convertToDTOList(medicamentos);
    }


    private List<CreateMedicamentoDTO> convertToMedicamentoDTOs(List<Medicamento> medicamentos) {
        List<CreateMedicamentoDTO> medicamentosDTO = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            medicamentosDTO.add(convertToMedicamentoDTO(medicamento));
        }
        return medicamentosDTO;
    }

    private CreateMedicamentoDTO convertToMedicamentoDTO(Medicamento medicamento) {
        CreateMedicamentoDTO createMedicamentoDTO = new CreateMedicamentoDTO();
        createMedicamentoDTO.setId(medicamento.getId());
        createMedicamentoDTO.setNome(medicamento.getNome());
        createMedicamentoDTO.setDescricao(medicamento.getDescricao());
        createMedicamentoDTO.setPreco(medicamento.getPreco());
        createMedicamentoDTO.setPrecoDesconto(medicamento.getPrecoDesconto());
        createMedicamentoDTO.setMarca(medicamento.getMarca());
        createMedicamentoDTO.setFabricante(medicamento.getFabricante());
        // Definir outras propriedades do MedicamentoDTO, se houver
        return createMedicamentoDTO;
    }

    private Medicamento convertToMedicamento(CreateMedicamentoDTO createMedicamentoDTO) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(createMedicamentoDTO.getNome());
        medicamento.setDescricao(createMedicamentoDTO.getDescricao());
        medicamento.setPreco(createMedicamentoDTO.getPreco());
        medicamento.setPrecoDesconto(createMedicamentoDTO.getPrecoDesconto());
        medicamento.setMarca(createMedicamentoDTO.getMarca());
        medicamento.setFabricante(createMedicamentoDTO.getFabricante());
        return medicamento;
    }
}
