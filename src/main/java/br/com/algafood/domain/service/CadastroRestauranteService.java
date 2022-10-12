package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.model.Restaurante;
import br.com.algafood.domain.repository.CozinhaRepository;
import br.com.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha com id %d não encontrada";
    public static final String RESTAURANTE_NAO_ENCONTRADO = "Restaurante de código %d não foi localizado";
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {

        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(
                        String.format(COZINHA_NAO_ENCONTRADA, cozinhaId)));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);


    }

    public Restaurante buscarOuFalhar(Long restauranteId){
        return restauranteRepository.findById(restauranteId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(
                        String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
    }

    public void excluir(Long restauranteId) {

        try {
            restauranteRepository.deleteById(restauranteId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(RESTAURANTE_NAO_ENCONTRADO, restauranteId));

        }
    }
}
