package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastroCozinhaService {

    public static final String COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso";
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);


    }


    public Cozinha buscarOuFalhar(Long cozinhaId){
        return cozinhaRepository.findById(cozinhaId).orElseThrow(() ->
                new CozinhaNaoEncontradaException(cozinhaId));
    }


    public void excluir(Long cozinhaId) {

        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(COZINHA_EM_USO, cozinhaId));

        }
    }

}
