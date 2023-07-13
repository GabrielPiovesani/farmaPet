package com.br.farmapet.controller;

import com.br.farmapet.domain.dto.request.CreateMedicamentoDTO;
import com.br.farmapet.domain.dto.request.CreateMedicamentoInjetavelDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoDTO;
import com.br.farmapet.domain.dto.response.UpdateMedicamentoDTO;
import com.br.farmapet.domain.enums.Ordenacao;
import com.br.farmapet.infra.exception.InvalidOrdenacaoException;
import com.br.farmapet.infra.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/cadastrar-um-medicamento")
    public ResponseEntity<CreateMedicamentoDTO> cadastrarMedicamento(@RequestBody CreateMedicamentoDTO createMedicamentoDTO) {
        CreateMedicamentoDTO novoCreateMedicamentoDTO = medicamentoService.cadastrarMedicamento(createMedicamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCreateMedicamentoDTO);
    }

    @PostMapping("/cadastrar-lista-de-medicamentos")
    public ResponseEntity<List<CreateMedicamentoDTO>> cadastrarMedicamentos(@RequestBody List<CreateMedicamentoDTO> medicamentosDTO) {
        List<CreateMedicamentoDTO> novosMedicamentosDTO = medicamentoService.cadastrarMedicamentos(medicamentosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosMedicamentosDTO);
    }


    @PutMapping("/atualizar-preco/{id}")
    public ResponseEntity<UpdateMedicamentoDTO> atualizarMedicamento(
            @PathVariable Long id, @RequestBody UpdateMedicamentoDTO updateMedicamentoDTO) {
        UpdateMedicamentoDTO medicamentoAtualizadoDTO = medicamentoService.atualizarMedicamento(id, updateMedicamentoDTO);
        return ResponseEntity.ok(medicamentoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        medicamentoService.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista-de-medicamentos")
    public ResponseEntity<Page<GetMedicamentoDTO>> listarMedicamentos(Pageable pageable) {
        Page<GetMedicamentoDTO> medicamentosDTO = medicamentoService.listarMedicamentos(pageable);
        return ResponseEntity.ok(medicamentosDTO);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<GetMedicamentoDTO>> filtrarMedicamentos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String fabricante,
            @RequestParam(required = false) Double precoMenorQue,
            @RequestParam(required = false) Double precoMaiorQue) {
        List<GetMedicamentoDTO> medicamentosFiltradosDTO = medicamentoService.filtrarMedicamentos(marca, fabricante, precoMenorQue, precoMaiorQue);
        return ResponseEntity.ok(medicamentosFiltradosDTO);
    }


    @GetMapping("/ordenar-medicamentos-pelo")
    public ResponseEntity<List<GetMedicamentoDTO>> ordenarMedicamentos(@RequestParam String ordenacao) {
    Ordenacao opcaoOrdenacao;

    try {
        opcaoOrdenacao = Ordenacao.valueOf(ordenacao.toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new InvalidOrdenacaoException("Opção de ordenação inválida: Opçoes: menor_preco maior_preco o que voce colocou: " + ordenacao);
    }

    List<GetMedicamentoDTO> medicamentosOrdenadosDTO = medicamentoService.ordenarMedicamentos(opcaoOrdenacao);
    return ResponseEntity.ok(medicamentosOrdenadosDTO);
}

}
