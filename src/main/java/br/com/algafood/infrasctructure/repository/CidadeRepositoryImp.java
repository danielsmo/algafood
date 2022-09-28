package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImp implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("select c from Cidade c",Cidade.class).getResultList();
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class,id);
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade) {
        cidade = buscar(cidade.getId());
        manager.remove(cidade);

    }
}
