package com.br.farmapet.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateMedicamentoDTO {


    private double preco;
    private double precoDesconto;

    public UpdateMedicamentoDTO(double preco, double precoDesconto) {
        this.preco = preco;
        this.precoDesconto = precoDesconto;
    }


}
