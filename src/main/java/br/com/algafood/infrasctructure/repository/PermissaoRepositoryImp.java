package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.Permissao;
import br.com.algafood.repository.PermissaoRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImp implements PermissaoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("select p from Permissao p",Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class,id);
    }

    @Override
    public Permissao adicionar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);

    }
}
