package br.com.algafood.api.controller;

import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRepository;
import br.com.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List> listar() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        return ResponseEntity.ok().body(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {

        return cadastroCozinhaService.buscarOuFalhar(cozinhaId);


    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        cadastroCozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);

    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);


        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cadastroCozinhaService.salvar(cozinhaAtual);

    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);

    }
}
