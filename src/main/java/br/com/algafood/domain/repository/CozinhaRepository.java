package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> nome(String nome);

    List<Cozinha> findByNome(String nome);


}
