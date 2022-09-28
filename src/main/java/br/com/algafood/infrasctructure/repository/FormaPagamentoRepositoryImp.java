package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.FormaPagamento;
import br.com.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImp implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        return manager.createQuery("select f from FormaPagamento f",FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class,id);
    }

    @Override
    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = buscar(formaPagamento.getId());
        manager.remove(formaPagamento);

    }
}
