package com.br.farmapet.domain.dto.request;

import com.br.farmapet.domain.model.Medicamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMedicamentoDTO {

    private String nome;
    private String descricao;
    private double preco;
    private double precoDesconto;
    private String marca;
    private String fabricante;


    public static List<CreateMedicamentoDTO> convertToDTOList(List<Medicamento> medicamentos) {
        List<CreateMedicamentoDTO> createMedicamentoDTOS = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            CreateMedicamentoDTO createMedicamentoDTO = new CreateMedicamentoDTO();
            BeanUtils.copyProperties(medicamento, createMedicamentoDTO);
            createMedicamentoDTOS.add(createMedicamentoDTO);
        }
        return createMedicamentoDTOS;
}

}
