package com.br.farmapet.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento_injetavel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoInjetavel extends Medicamento {

    private String tipoAplicaçao;
}
