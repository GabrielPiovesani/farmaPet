package com.br.farmapet.domain.dto.response;

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
public class DeleteMedicamentoDTO {

    private long id;
    private String nome;
    private String descricao;
    private String marca;
    private String fabricante;


    public static List<DeleteMedicamentoDTO> convertToDTOList(List<Medicamento> medicamentos) {
        List<DeleteMedicamentoDTO> deleteMedicamentoDTOS = new ArrayList<>();
        for (Medicamento medicamento : medicamentos) {
            DeleteMedicamentoDTO deleteMedicamentoDTO = new DeleteMedicamentoDTO();
            BeanUtils.copyProperties(medicamento, deleteMedicamentoDTO);
            deleteMedicamentoDTOS.add(deleteMedicamentoDTO);
        }
        return deleteMedicamentoDTOS;
    }
}
