package br.com.algafood.api.controller;

import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/teste")
@RestController
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("/{nome}")
    public List<Cozinha> listar(@PathVariable String nome) {
        return cozinhaRepository.findByNome(nome);
    }
}
