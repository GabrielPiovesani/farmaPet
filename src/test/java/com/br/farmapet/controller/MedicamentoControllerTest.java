package com.br.farmapet.controller;

import com.br.farmapet.domain.dto.request.CreateMedicamentoDTO;
import com.br.farmapet.domain.dto.request.GetMedicamentoDTO;
import com.br.farmapet.infra.service.MedicamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MedicamentoControllerTest {

    @Mock
    private MedicamentoService medicamentoService;

    private MedicamentoController medicamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        medicamentoController = new MedicamentoController(medicamentoService);
    }

    @Test
    void cadastrarMedicamento_deveRetornarMedicamentoCriado() {
        // Preparação
        CreateMedicamentoDTO createMedicamentoDTO = new CreateMedicamentoDTO();
        CreateMedicamentoDTO novoCreateMedicamentoDTO = new CreateMedicamentoDTO();
        when(medicamentoService.cadastrarMedicamento(createMedicamentoDTO)).thenReturn(novoCreateMedicamentoDTO);

        // Execução
        ResponseEntity<CreateMedicamentoDTO> response = medicamentoController.cadastrarMedicamento(createMedicamentoDTO);

        // Verificação
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(novoCreateMedicamentoDTO, response.getBody());
        verify(medicamentoService, times(1)).cadastrarMedicamento(createMedicamentoDTO);
        verifyNoMoreInteractions(medicamentoService);
    }

    @Test
    void listarMedicamentos_deveRetornarListaDeMedicamentos() {
        // Preparação
        List<GetMedicamentoDTO> medicamentosDTO = new ArrayList<>();
        Page<GetMedicamentoDTO> medicamentosPage = new PageImpl<>(medicamentosDTO);
        Pageable pageable = Pageable.unpaged();
        when(medicamentoService.listarMedicamentos(pageable)).thenReturn(medicamentosPage);

        // Execução
        ResponseEntity<Page<GetMedicamentoDTO>> response = medicamentoController.listarMedicamentos(pageable);

        // Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicamentosPage, response.getBody());
        verify(medicamentoService, times(1)).listarMedicamentos(pageable);
        verifyNoMoreInteractions(medicamentoService);
    }



}
