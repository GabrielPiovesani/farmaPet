package com.br.farmapet.controller;

import com.br.farmapet.domain.dto.request.CreateMedicamentoDTO;
import com.br.farmapet.domain.dto.request.CreateMedicamentoInjetavelDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoInjetavelDTO;
import com.br.farmapet.domain.dto.response.UpdateMedicamentoDTO;
import com.br.farmapet.domain.enums.Ordenacao;
import com.br.farmapet.domain.model.MedicamentoInjetavel;
import com.br.farmapet.infra.exception.InvalidOrdenacaoException;
import com.br.farmapet.infra.service.MedicamentoInjetavelService;
import com.br.farmapet.infra.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicamentosInjetaveis")
public class MedicamentoInjetavelController  {
    private final MedicamentoInjetavelService medicamentoInjetavelService;

    @Autowired
    public MedicamentoInjetavelController(MedicamentoInjetavelService medicamentoInjetavelService) {
        this.medicamentoInjetavelService = medicamentoInjetavelService;
    }


    @PostMapping("/cadastrar-um-medicamento-injetavel")
    public ResponseEntity<CreateMedicamentoInjetavelDTO> cadastrarMedicamentoInjetavel(@RequestBody CreateMedicamentoInjetavelDTO createMedicamentoInjetavelDTO) {
        CreateMedicamentoInjetavelDTO novoCreateMedicamentoInjetavelDTO = medicamentoInjetavelService.cadastrarMedicamentoInjetavel(createMedicamentoInjetavelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCreateMedicamentoInjetavelDTO);
    }

    @PostMapping("/cadastrar-lista-de-medicamentos-injetaveis")
    public ResponseEntity<List<CreateMedicamentoInjetavelDTO>> cadastrarListaMedicamentosInjetaveis(@RequestBody List<CreateMedicamentoInjetavelDTO> medicamentosInjetaveisDTO) {
        List<CreateMedicamentoInjetavelDTO> novosMedicamentosInjetaveisDTO = medicamentoInjetavelService.cadastrarListaMedicamentosInjetaveis(medicamentosInjetaveisDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novosMedicamentosInjetaveisDTO);
    }


    @GetMapping("/medicamentos-injetaveis")
    public ResponseEntity<List<GetMedicamentoInjetavelDTO>> buscarMedicamentosInjetaveis() {
        List<GetMedicamentoInjetavelDTO> medicamentosInjetaveisDTO = medicamentoInjetavelService.buscarMedicamentosInjetaveis();
        return ResponseEntity.ok(medicamentosInjetaveisDTO);
    }



}
