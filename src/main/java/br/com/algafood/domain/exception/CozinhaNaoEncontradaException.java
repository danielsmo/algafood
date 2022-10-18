package br.com.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format(
                "A Cozinha de código %d não foi localizada", cozinhaId));
    }

    public CozinhaNaoEncontradaException(String message) {
        super(message);
    }
}
