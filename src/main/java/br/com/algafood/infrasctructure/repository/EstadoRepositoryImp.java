package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImp implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public List<Estado> listar() {
        return manager.createQuery("select e from Estado e", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    @Transactional
    public void remover(Long estadoId) {
        Estado estado = buscar(estadoId);
        if (estado == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(estado);
    }
}
