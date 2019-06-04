package br.ufrn.imd.atendimentoframwork.controller;

import br.ufrn.imd.atendimentoframwork.exception.GuicheJaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(GuicheJaExisteException.class)
    public ResponseEntity<?> handleGuicheJaExisteException(GuicheJaExisteException gjeException) {
        return new ResponseEntity<>(gjeException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
