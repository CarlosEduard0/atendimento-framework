package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.model.TipoServico;
import br.ufrn.imd.atendimentoframwork.service.interfaces.TipoServicoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tiposServicos")
public class TipoServicoController {
    private final TipoServicoService tipoServicoService;

    @Autowired
    public TipoServicoController(TipoServicoService tipoServicoService) {
        this.tipoServicoService = tipoServicoService;
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de todos os tipos de serviços", response = TipoServico[].class)
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(tipoServicoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna o tipo de serviço de acordo o ID passado como parâmetro")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(tipoServicoService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Salva o tipo de serviço no banco de dados", response = TipoServico.class)
    public ResponseEntity<?> save(@RequestBody TipoServico tipoServico) {
        return new ResponseEntity<>(tipoServicoService.save(tipoServico), HttpStatus.OK);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza o tipo de serviço")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TipoServico tipoServico) {
        tipoServico.setId(id);
        tipoServicoService.update(tipoServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta o tipo de serviço")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        tipoServicoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
