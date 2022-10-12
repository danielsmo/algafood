package br.com.algafood.api.controller;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.exception.EstadoNaoEncontradoException;
import br.com.algafood.domain.exception.NegocioException;
import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.repository.CidadeRepository;
import br.com.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {

        return cadastroCidadeService.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    public void adicionar(@RequestBody Cidade cidade) {

        try {
            cadastroCidadeService.salvar(cidade);
        } catch (EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage());
        }
    }


    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

        Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        try {
            return cadastroCidadeService.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public void excluir(@PathVariable Long cidadeId) {

      cadastroCidadeService.excluir(cidadeId);
    }

}
