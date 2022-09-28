package br.com.algafood.infrasctructure.repository;

import br.com.algafood.domain.model.Permissao;
import br.com.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {
        permissao = buscar(permissao.getId());
        manager.remove(permissao);

    }
}
