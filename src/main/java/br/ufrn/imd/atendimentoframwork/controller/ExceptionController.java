package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.erro.ErroDetalhes;
import br.ufrn.imd.atendimentoframwork.exception.GuicheException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(GuicheException.class)
    public ResponseEntity<?> handleGuicheJaExisteException(GuicheException gException) {
        return new ResponseEntity<>(new ErroDetalhes(gException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
