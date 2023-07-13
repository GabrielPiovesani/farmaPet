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
public class GetMedicamentoDTO {

    private long id;
    private String nome;
    private String descricao;
    private double preco;
    private double precoDesconto;
    private String marca;
    private String fabricante;


    public static List<GetMedicamentoDTO> convertToDTOList(List<Medicamento> medicamentos) {
        List<GetMedicamentoDTO> createGetMedicamentoDTOS = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            GetMedicamentoDTO createGetMedicamentoDTO = new GetMedicamentoDTO();
            BeanUtils.copyProperties(medicamento, createGetMedicamentoDTO);
            createGetMedicamentoDTOS.add(createGetMedicamentoDTO);
        }
        return createGetMedicamentoDTOS;
}

}
