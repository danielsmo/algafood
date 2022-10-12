package br.com.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    public RestauranteNaoEncontradoException(Long estadoId) {
        this(String.format(
                "O Restaurante de código %d não foi localizado",estadoId));
    }

    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }
}
