package com.br.farmapet.repository;

import com.br.farmapet.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByMarca(String marca);
    List<Medicamento> findByFabricante(String fabricante);
    List<Medicamento> findByPrecoLessThan(double preco);
    List<Medicamento> findByPrecoGreaterThan(double preco);
    List<Medicamento> findAllByOrderByPrecoAsc();
    List<Medicamento> findAllByOrderByPrecoDesc();
}
