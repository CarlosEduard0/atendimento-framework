package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.service.GuicheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guiches")
public class GuicheController {
    private final GuicheService guicheService;

    @Autowired
    public GuicheController(GuicheService guicheService) {
        this.guicheService = guicheService;
    }

    @GetMapping
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>("Lista de Guichês", HttpStatus.OK);
    }
}
