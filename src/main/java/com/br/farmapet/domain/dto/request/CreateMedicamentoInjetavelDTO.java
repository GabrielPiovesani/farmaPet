package com.br.farmapet.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMedicamentoInjetavelDTO extends CreateMedicamentoDTO {

    private String tipoAplicacao;

}
