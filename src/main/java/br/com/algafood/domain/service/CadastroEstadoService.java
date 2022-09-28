package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;


    public Estado salvar(Estado estado){
        return estadoRepository.salvar(estado);
    }


    public void excluir(Long estadoId){

        try {
            estadoRepository.remover(estadoId);

        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("O estado de código %d não pode ser removida pois está em uso",estadoId));

        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("O estado de código %d não foi localizado",estadoId));

        }

    }
}
