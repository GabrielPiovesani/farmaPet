package com.br.farmapet.infra.repository;

import com.br.farmapet.domain.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoInjetavelRepository extends JpaRepository<Medicamento, Long> {
}
