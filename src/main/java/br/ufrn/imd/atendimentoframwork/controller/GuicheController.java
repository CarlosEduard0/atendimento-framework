package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.model.Guiche;
import br.ufrn.imd.atendimentoframwork.service.interfaces.GuicheService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guiches")
public class GuicheController {
    private final GuicheService guicheService;

    @Autowired
    public GuicheController(@Qualifier("clinicaGuicheService") GuicheService guicheService) {
        this.guicheService = guicheService;
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de guichês", response = Guiche[].class)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(guicheService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna o guichê encontrado", response = Guiche.class)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(guicheService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Salva o guichê no banco de dados", response = Guiche.class)
    public ResponseEntity<?> save(@RequestBody Guiche guiche) {
        return new ResponseEntity<>(guicheService.save(guiche), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza o guichê do ID passado como parâmetro")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Guiche guiche) {
        guiche.setId(id);
        guicheService.update(guiche);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}/ativar")
    @ApiOperation(value = "Faz a ativação do guichê")
    public ResponseEntity<?> ativar(@PathVariable("id") Long id) {
        guicheService.ativar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta o guichê com o ID especificado")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        guicheService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
