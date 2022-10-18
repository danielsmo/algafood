package br.com.algafood.api.controller;

import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;
import br.com.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }


    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {

        return cadastroEstadoService.buscarOuFalhar(estadoId);


    }


    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {

        estadoRepository.save(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estado);


    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return cadastroEstadoService.salvar(estadoAtual);

    }


    @DeleteMapping("/{estadoId}")
    public void excluir(@PathVariable Long estadoId) {

        cadastroEstadoService.excluir(estadoId);

    }

}
