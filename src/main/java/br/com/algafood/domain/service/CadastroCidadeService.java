package br.com.algafood.domain.service;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.CidadeRepository;
import br.com.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String CIDADE_NAO_ENCONTRADA = "Cidade de código %d não foi localizada";
    public static final String ESTADO_NAO_ENCONTRADO = "Estado com id %d não foi encontrado";
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {

        Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(
                        String.format(ESTADO_NAO_ENCONTRADO, cidade.getEstado().getId())));


        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }


    public Cidade buscarOuFalhar(Long cidadeId){
        return cidadeRepository.findById(cidadeId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(
                        String.format(CIDADE_NAO_ENCONTRADA, cidadeId)));
    }


    public void excluir(Long cidadeId) {

        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(CIDADE_NAO_ENCONTRADA, cidadeId));

        }


    }
}
