package com.br.farmapet.service;

import com.br.farmapet.domain.Carrinho;
import com.br.farmapet.domain.Medicamento;
import com.br.farmapet.dto.CarrinhoDTO;
import com.br.farmapet.dto.CreateMedicamentoDTO;
import com.br.farmapet.repository.CarrinhoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public CarrinhoDTO criarCarrinho(CarrinhoDTO carrinhoDTO) {

        Carrinho carrinho = new Carrinho();
        carrinho.setMedicamentos(new ArrayList<>(carrinhoDTO.getMedicamentos()));
        Carrinho novoCarrinho = carrinhoRepository.save(carrinho);
        CarrinhoDTO novoCarrinhoDTO = new CarrinhoDTO();
        novoCarrinhoDTO.setId(novoCarrinho.getId());
        novoCarrinhoDTO.setMedicamentos(new ArrayList<>(novoCarrinho.getMedicamentos()));
        return novoCarrinhoDTO;
    }

    private List<CreateMedicamentoDTO> convertToMedicamentoDTOs(List<Medicamento> medicamentos) {
        List<CreateMedicamentoDTO> createMedicamentoDTOS = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            CreateMedicamentoDTO createMedicamentoDTO = new CreateMedicamentoDTO();
            BeanUtils.copyProperties(medicamento, createMedicamentoDTO);
            createMedicamentoDTOS.add(createMedicamentoDTO);
        }
        return createMedicamentoDTOS;
    }

    private List<Medicamento> convertToMedicamentos(List<CreateMedicamentoDTO> createMedicamentoDTOS) {
        List<Medicamento> medicamentos = new ArrayList<>();
        for (CreateMedicamentoDTO createMedicamentoDTO : createMedicamentoDTOS) {
            Medicamento medicamento = new Medicamento();
            BeanUtils.copyProperties(createMedicamentoDTO, medicamento);
            medicamentos.add(medicamento);
        }
        return medicamentos;
    }
}
