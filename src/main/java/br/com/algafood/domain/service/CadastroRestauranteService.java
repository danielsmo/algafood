package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.model.Restaurante;
import br.com.algafood.domain.repository.CozinhaRepository;
import br.com.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {

            throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha com id %d não encontrada",cozinhaId));

        }
        return restauranteRepository.salvar(restaurante);


    }
}
