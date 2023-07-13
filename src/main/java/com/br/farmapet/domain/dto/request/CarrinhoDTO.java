package com.br.farmapet.domain.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class CarrinhoDTO {
    private Long id;
    private List<CarrinhoItemDTO> medicamentos;
}