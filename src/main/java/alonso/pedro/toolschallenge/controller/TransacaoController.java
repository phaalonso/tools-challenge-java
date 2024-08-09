package alonso.pedro.toolschallenge.controller;

import alonso.pedro.toolschallenge.model.request.TransacaoRequest;
import alonso.pedro.toolschallenge.repository.TransacaoRepository;
import alonso.pedro.toolschallenge.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TransacaoController {
    private final TransacaoRepository transacaoRepository;
    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoRequest> realizarTransacao(@RequestBody TransacaoRequest transacao) {

        transacaoService.insereTransacao(transacao);

        return ResponseEntity.status(201).body(transacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoRequest> consultarTransacao(@PathVariable("id") String id) {
        log.info("Consultando a transação de id: {}", id);

        var transacao = transacaoService.findById(id);

        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/estornar/{id}")
    public ResponseEntity<TransacaoRequest> estornarTransacao(@PathVariable("id") String id) {
        log.info("Realizando estorno do id: {}", id);

        var transacao = transacaoService.realizaEstorno(id);

        return ResponseEntity.ok(transacao);
    }
}

