package com.br.farmapet.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "carrinho")
@Data
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id")
    private List<Medicamento> medicamentos;

}
