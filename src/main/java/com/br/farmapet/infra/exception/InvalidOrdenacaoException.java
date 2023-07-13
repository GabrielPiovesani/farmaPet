package com.br.farmapet.infra.exception;

public class InvalidOrdenacaoException extends RuntimeException {
    public InvalidOrdenacaoException(String message) {
        super(message);
    }
}
