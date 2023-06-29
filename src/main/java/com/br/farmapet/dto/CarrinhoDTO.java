package com.br.farmapet.dto;

import com.br.farmapet.domain.Medicamento;
import lombok.Data;

import java.util.List;
@Data
public class CarrinhoDTO {

    private Long id;

    private List<Medicamento> medicamentos;
}
