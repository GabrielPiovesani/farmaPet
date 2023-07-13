package com.br.farmapet.infra.service;

import com.br.farmapet.domain.model.Medicamento;
import com.br.farmapet.domain.dto.request.CreateMedicamentoDTO;
import com.br.farmapet.domain.dto.response.DeleteMedicamentoDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoDTO;
import com.br.farmapet.domain.dto.response.UpdateMedicamentoDTO;
import com.br.farmapet.domain.enums.Ordenacao;
import com.br.farmapet.infra.exception.MedicamentoNotFoundException;
import com.br.farmapet.infra.repository.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<CreateMedicamentoDTO> cadastrarMedicamentos(List<CreateMedicamentoDTO> medicamentosDTO) {
        List<CreateMedicamentoDTO> novosMedicamentosDTO = new ArrayList<>();

        for (CreateMedicamentoDTO medicamentoDTO : medicamentosDTO) {
            Medicamento medicamento = new Medicamento();
            BeanUtils.copyProperties(medicamentoDTO, medicamento);
            Medicamento novoMedicamento = medicamentoRepository.save(medicamento);
            CreateMedicamentoDTO novoCreateMedicamentoDTO = new CreateMedicamentoDTO();
            BeanUtils.copyProperties(novoMedicamento, novoCreateMedicamentoDTO);
            novosMedicamentosDTO.add(novoCreateMedicamentoDTO);
        }

        return novosMedicamentosDTO;
    }
    public CreateMedicamentoDTO cadastrarMedicamento(CreateMedicamentoDTO createMedicamentoDTO) {
        Medicamento medicamento = new Medicamento();
        BeanUtils.copyProperties(createMedicamentoDTO, medicamento);
        Medicamento novoMedicamento = medicamentoRepository.save(medicamento);
        CreateMedicamentoDTO novoCreateMedicamentoDTO = new CreateMedicamentoDTO();
        BeanUtils.copyProperties(novoMedicamento, novoCreateMedicamentoDTO);
        return novoCreateMedicamentoDTO;
    }

    public UpdateMedicamentoDTO atualizarMedicamento(Long id, UpdateMedicamentoDTO createMedicamentoDTO) {
        Optional<Medicamento> optionalMedicamento = medicamentoRepository.findById(id);
        if (optionalMedicamento.isPresent()) {
            Medicamento medicamento = optionalMedicamento.get();

            double preco = createMedicamentoDTO.getPreco();
            double precoDesconto = createMedicamentoDTO.getPrecoDesconto();

            if (precoDesconto != 0.0) {
                medicamento.setPrecoDesconto(precoDesconto);
            }

            medicamento.setPreco(preco);

            Medicamento medicamentoAtualizado = medicamentoRepository.save(medicamento);
            return new UpdateMedicamentoDTO(preco, precoDesconto);
        } else {
            throw new MedicamentoNotFoundException("Medicamento não encontrado com o ID: " + id);
        }
    }



    public DeleteMedicamentoDTO deletarMedicamento(Long id) {
        Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(id);

        if (medicamentoOptional.isPresent()) {
            Medicamento medicamento = medicamentoOptional.get();
            medicamentoRepository.deleteById(id);

            DeleteMedicamentoDTO deleteMedicamentoDTO = new DeleteMedicamentoDTO();
            BeanUtils.copyProperties(medicamento, deleteMedicamentoDTO);

            return deleteMedicamentoDTO;
        } else {
            throw new MedicamentoNotFoundException("Medicamento não encontrado com o ID: " + id);
        }
    }



    public Page<GetMedicamentoDTO> listarMedicamentos(Pageable pageable) {
        Page<Medicamento> medicamentos = medicamentoRepository.findAll(pageable);
        Page<GetMedicamentoDTO> medicamentosDTO = medicamentos.map(m -> {
            GetMedicamentoDTO medicamentoDTO = new GetMedicamentoDTO();
            medicamentoDTO.setId(m.getId());
            medicamentoDTO.setNome(m.getNome());
            medicamentoDTO.setDescricao(m.getDescricao());
            medicamentoDTO.setPreco(m.getPreco());
            medicamentoDTO.setMarca(m.getMarca());
            medicamentoDTO.setPrecoDesconto(m.getPrecoDesconto());
            medicamentoDTO.setFabricante(m.getFabricante());

            return medicamentoDTO;
        });
        return medicamentosDTO;
    }

    public List<GetMedicamentoDTO> filtrarMedicamentos(String marca, String fabricante, Double precoMenorQue, Double precoMaiorQue) {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        List<Medicamento> medicamentosFiltrados = medicamentos.stream()
                .filter(medicamento -> marca == null || medicamento.getMarca().equalsIgnoreCase(marca))
                .filter(medicamento -> fabricante == null || medicamento.getFabricante().equalsIgnoreCase(fabricante))
                .filter(medicamento -> precoMenorQue == null || medicamento.getPreco() < precoMenorQue)
                .filter(medicamento -> precoMaiorQue == null || medicamento.getPreco() > precoMaiorQue)
                .collect(Collectors.toList());

        return GetMedicamentoDTO.convertToDTOList(medicamentosFiltrados);
    }


    public List<GetMedicamentoDTO> ordenarMedicamentos(Ordenacao ordenacao) {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();

        switch (ordenacao) {
            case MENOR_PRECO:
                medicamentos.sort((m1, m2) -> Double.compare(m1.getPreco(), m2.getPreco()));
                break;
            case MAIOR_PRECO:
                medicamentos.sort((m1, m2) -> Double.compare(m2.getPreco(), m1.getPreco()));
                break;
            default:
                // Lógica de ordenação padrão, se necessário
                break;
        }

        return GetMedicamentoDTO.convertToDTOList(medicamentos);
    }


}
