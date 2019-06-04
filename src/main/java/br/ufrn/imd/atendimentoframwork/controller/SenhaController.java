package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.model.Senha;
import br.ufrn.imd.atendimentoframwork.service.interfaces.SenhaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("senhas")
public class SenhaController {
    private final SenhaService senhaService;

    @Autowired
    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PostMapping
    @ApiOperation(value = "Adiciona senha ao banco de dados", response = Senha.class)
    public ResponseEntity<?> save(@RequestBody Senha senha) {
        return new ResponseEntity<>(senhaService.save(senha), HttpStatus.OK);
    }
}
