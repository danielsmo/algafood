package br.com.algafood.repository;

import br.com.algafood.domain.model.Cidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(Long id);
    Cidade salvar (Cidade cidade);
    void remover(Cidade cidade);
}
