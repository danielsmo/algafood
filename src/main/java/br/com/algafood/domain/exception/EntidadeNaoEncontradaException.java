package br.com.algafood.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
