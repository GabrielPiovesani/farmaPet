package com.br.farmapet.domain.dto.request;

import lombok.Data;

@Data
public class CarrinhoItemDTO {
    private String nomeMedicamento;
    private String marca;
    private double valor;


}
