package com.br.farmapet.infra.exception;

public class MedicamentoNotFoundException extends RuntimeException {
    public MedicamentoNotFoundException(String message) {
        super(message);
    }
}
