package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.model.Atendimento;
import br.ufrn.imd.atendimentoframwork.service.interfaces.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("atendimentos")
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(atendimentoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(atendimentoService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Atendimento atendimento) {
        return new ResponseEntity<>(atendimentoService.save(atendimento), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Atendimento atendimento) {
        atendimento.setId(id);
        atendimentoService.update(atendimento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        atendimentoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
