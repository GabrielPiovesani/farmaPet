package com.br.farmapet.controller;

import com.br.farmapet.dto.CreateMedicamentoDTO;
import com.br.farmapet.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<CreateMedicamentoDTO> cadastrarMedicamento(@RequestBody CreateMedicamentoDTO createMedicamentoDTO) {
        CreateMedicamentoDTO novoCreateMedicamentoDTO = medicamentoService.cadastrarMedicamento(createMedicamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCreateMedicamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateMedicamentoDTO> atualizarMedicamento(
            @PathVariable Long id, @RequestBody CreateMedicamentoDTO createMedicamentoDTO) {
        CreateMedicamentoDTO medicamentoAtualizadoDTO = medicamentoService.atualizarMedicamento(id, createMedicamentoDTO);
        return ResponseEntity.ok(medicamentoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        medicamentoService.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CreateMedicamentoDTO>> listarMedicamentos() {
        var medicamentosDTO = medicamentoService.listarMedicamentos();
        return ResponseEntity.ok(medicamentosDTO);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<CreateMedicamentoDTO>> filtrarMedicamentos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) Double precoMenorQue,
            @RequestParam(required = false) Double precoMaiorQue) {
        List<CreateMedicamentoDTO> medicamentosFiltradosDTO = medicamentoService.filtrarMedicamentos(marca, fabricante, precoMenorQue, precoMaiorQue);
        return ResponseEntity.ok(medicamentosFiltradosDTO);
    }

    @GetMapping("/ordenar")
    public ResponseEntity<List<CreateMedicamentoDTO>> ordenarMedicamentos(@RequestParam String ordenacao) {
        List<CreateMedicamentoDTO> medicamentosOrdenadosDTO = medicamentoService.ordenarMedicamentos(ordenacao);
        return ResponseEntity.ok(medicamentosOrdenadosDTO);
    }
}
