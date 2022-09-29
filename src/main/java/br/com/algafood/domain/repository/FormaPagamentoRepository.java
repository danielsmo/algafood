package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.Normalizer;
import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento,Long> {


}
