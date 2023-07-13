package com.br.farmapet.infra.service;

import com.br.farmapet.domain.model.Carrinho;
import com.br.farmapet.domain.model.Medicamento;
import com.br.farmapet.domain.dto.request.CarrinhoDTO;
import com.br.farmapet.domain.dto.request.CarrinhoItemDTO;
import com.br.farmapet.infra.exception.CarrinhoNotFoundException;
import com.br.farmapet.infra.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public CarrinhoDTO criarCarrinho(CarrinhoDTO carrinhoDTO) {
        Carrinho carrinho = new Carrinho();
        carrinho.setMedicamentos(convertToMedicamentos(carrinhoDTO.getMedicamentos()));
        Carrinho novoCarrinho = carrinhoRepository.save(carrinho);
        CarrinhoDTO novoCarrinhoDTO = new CarrinhoDTO();
        novoCarrinhoDTO.setId(novoCarrinho.getId());
        novoCarrinhoDTO.setMedicamentos(carrinhoDTO.getMedicamentos());
        return novoCarrinhoDTO;
    }

    private List<Medicamento> convertToMedicamentos(List<CarrinhoItemDTO> carrinhoItemDTOs) {
        List<Medicamento> medicamentos = new ArrayList<>();
        for (CarrinhoItemDTO carrinhoItemDTO : carrinhoItemDTOs) {
            Medicamento medicamento = new Medicamento();
            medicamento.setNome(carrinhoItemDTO.getNomeMedicamento());
            medicamento.setMarca(carrinhoItemDTO.getMarca());
            medicamento.setPreco(carrinhoItemDTO.getValor());
            medicamentos.add(medicamento);
        }
        return medicamentos;
    }

    public CarrinhoDTO obterCarrinho(Long id) {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(id);
        if (carrinhoOptional.isPresent()) {
            Carrinho carrinho = carrinhoOptional.get();

            CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
            carrinhoDTO.setId(carrinho.getId());
            carrinhoDTO.setMedicamentos(carrinhoDTO.getMedicamentos());

            return carrinhoDTO;
        } else {
            throw new CarrinhoNotFoundException("Carrinho não encontrado");
        }
    }
}



