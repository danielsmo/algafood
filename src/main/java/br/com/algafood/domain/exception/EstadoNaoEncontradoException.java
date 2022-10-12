package br.com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public EstadoNaoEncontradoException(Long estadoId) {
        this(String.format(
                "O estado de código %d não foi localizado",estadoId));
    }

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }
}
