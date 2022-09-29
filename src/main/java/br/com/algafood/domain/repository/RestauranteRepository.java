package br.com.algafood.domain.repository;

import br.com.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {

}
