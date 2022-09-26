package br.com.algafood.repository;

import br.com.algafood.domain.model.FormaPagamento;

import java.text.Normalizer;
import java.util.List;

public interface FormaPagamentoRepository {


    List<FormaPagamento> listar();
    FormaPagamento buscar (Long id);
    FormaPagamento adicionar (FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);

}
