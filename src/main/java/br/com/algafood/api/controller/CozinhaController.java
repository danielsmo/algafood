package br.com.algafood.api.controller;

import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRepository;
import br.com.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List> listar(){
        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        return ResponseEntity.ok().body(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<?> buscar(@PathVariable Long cozinhaId){

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));


        return ResponseEntity.ok().body(cozinha);


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha){
        cadastroCozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

    }
    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.findById(cozinhaId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(cozinha,cozinhaAtual, "id");
        cadastroCozinhaService.salvar(cozinhaAtual);
        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<?> deletar(@PathVariable Long cozinhaId) {

                try {
                    cadastroCozinhaService.excluir(cozinhaId);

                    return ResponseEntity.noContent().build();
                }catch (EntidadeEmUsoException e){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
                } catch (EntidadeNaoEncontradaException e){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

                }
    }
}
