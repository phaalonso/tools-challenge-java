package alonso.pedro.toolschallenge.service;

import alonso.pedro.toolschallenge.Mapper;
import alonso.pedro.toolschallenge.exception.TransacaoNaoEncontradaException;
import alonso.pedro.toolschallenge.model.TipoStatus;
import alonso.pedro.toolschallenge.model.request.TransacaoRequest;
import alonso.pedro.toolschallenge.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;

    public TransacaoRequest findById(String id) {
        return transacaoRepository.findById(id)
                .map(Mapper::toRequest)
                .orElseThrow(TransacaoNaoEncontradaException::new);
    }

    @Transactional
    public void insereTransacao(TransacaoRequest transacao) {
        transacao.getDescricao().setStatus(TipoStatus.AUTORIZADO);
        transacao.getDescricao().setNsu("123102983");
        transacao.getDescricao().setCodigoAutorizacao("123102983");

        transacaoRepository.insere(Mapper.toDTO(transacao));
    }

    @Transactional
    public TransacaoRequest realizaEstorno(String id) {
        return transacaoRepository.atualizaStatus(id, TipoStatus.CANCELADO)
                .map(Mapper::toRequest)
                .orElseThrow(TransacaoNaoEncontradaException::new);
    }
}
