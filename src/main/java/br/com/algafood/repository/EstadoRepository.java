package br.com.algafood.repository;

import br.com.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar (Long id);
    Estado adicionar (Estado estado);
    void remover(Estado estado);
}
