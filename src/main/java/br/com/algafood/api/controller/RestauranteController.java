package br.com.algafood.api.controller;

import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.model.Restaurante;
import br.com.algafood.domain.repository.RestauranteRepository;
import br.com.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List> listar() {
        List<Restaurante> restaurantes = restauranteRepository.listar();

        return ResponseEntity.ok().body(restaurantes);
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {

        Restaurante restaurante = restauranteRepository.buscar(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {

        try {
            restaurante = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {


        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }


        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

        try {
            cadastroRestauranteService.salvar(restauranteAtual);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> excluir(@PathVariable Long restauranteId){

        Restaurante restaurante = restauranteRepository.buscar(restauranteId);

        if (restaurante == null){
            return ResponseEntity.notFound().build();
        }
        restauranteRepository.remover(restaurante);
        return ResponseEntity.noContent().build();


    }
}
