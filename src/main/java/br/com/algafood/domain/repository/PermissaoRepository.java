package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar (Long id);
    Permissao salvar (Permissao permissao);
    void remover(Permissao permissao);
}
