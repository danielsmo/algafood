package br.com.algafood.api.controller;

import br.com.algafood.domain.exception.EntidadeEmUsoException;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;
import br.com.algafood.domain.service.CadastroEstadoService;
import org.apache.coyote.Response;
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
    public List<Estado> listar(){
        return estadoRepository.listar();
    }


    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){

        Estado estado = estadoRepository.buscar(estadoId);

        if (estado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(estado);

    }


    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){

        estadoRepository.salvar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estado);


    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){

        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual == null){
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(estado,estadoAtual,"id");

        cadastroEstadoService.salvar(estado);

        return ResponseEntity.ok().body(estadoAtual);


    }


    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> excluir(@PathVariable Long estadoId){

        try {
            cadastroEstadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
