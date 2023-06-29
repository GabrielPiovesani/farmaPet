package com.br.farmapet.controller;

import com.br.farmapet.dto.CarrinhoDTO;
import com.br.farmapet.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


}
