package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EstadoNaoEncontradoException;
import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String ESTADO_EM_USO = "O estado de código %d não pode ser removido pois está em uso";
    @Autowired
    private EstadoRepository estadoRepository;


    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }


    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() ->
                new EstadoNaoEncontradoException(estadoId));
    }


    public void excluir(Long estadoId) {

        try {
            estadoRepository.deleteById(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(ESTADO_EM_USO, estadoId));

        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);

        }

    }
}
