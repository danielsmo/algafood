package br.com.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format(
                "A Cidade de código %d não foi localizada",cidadeId));
    }

    public CidadeNaoEncontradaException(String message) {
        super(message);
    }
}
