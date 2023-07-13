package com.br.farmapet.domain.model;



import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "medicamento_injetavel")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class MedicamentoInjetavel extends Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoAplicacao;



}
