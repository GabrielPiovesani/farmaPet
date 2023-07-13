package com.br.farmapet.infra.repository;

import com.br.farmapet.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}