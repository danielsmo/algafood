package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao,Long> {

}
