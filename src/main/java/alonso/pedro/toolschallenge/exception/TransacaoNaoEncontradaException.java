package alonso.pedro.toolschallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Transação não encontrada")
public class TransacaoNaoEncontradaException extends RuntimeException {
}
