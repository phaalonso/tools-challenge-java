package alonso.pedro.toolschallenge;

import alonso.pedro.toolschallenge.model.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHanlder {
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> duplicatedKeyException() {
        log.debug("Ocorreu uma tentativa de cadastrar uma transação com um id já existente");

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionDTO.builder()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message("Já existe um registro cadastrado com esse id")
                        .build());
    }
}
