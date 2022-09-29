package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
