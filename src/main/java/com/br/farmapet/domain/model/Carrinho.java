package com.br.farmapet.domain.model;

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

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id")
    private List<Medicamento> medicamentos;

}
