package com.br.farmapet.controller;

import com.br.farmapet.domain.dto.request.CarrinhoDTO;
import com.br.farmapet.infra.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping
    public CarrinhoDTO criarCarrinho(@RequestBody CarrinhoDTO carrinhoDTO) {
        return carrinhoService.criarCarrinho(carrinhoDTO);
    }

    @GetMapping("/{id}")
    public CarrinhoDTO obterCarrinho(@PathVariable Long id) {
        return carrinhoService.obterCarrinho(id);
    }
}


