package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class CozinhaRepositoryImp implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("select c from Cozinha c", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class,id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);

        if (cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);

    }
}
