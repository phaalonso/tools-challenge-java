package alonso.pedro.toolschallenge;

import alonso.pedro.toolschallenge.model.TipoPagamento;
import alonso.pedro.toolschallenge.model.TipoStatus;
import alonso.pedro.toolschallenge.model.dto.TransacaoDTO;
import alonso.pedro.toolschallenge.model.request.DescricaoPagamentoRequest;
import alonso.pedro.toolschallenge.model.request.FormaPagamentoRequest;
import alonso.pedro.toolschallenge.model.request.TransacaoRequest;

import java.math.BigDecimal;

public class Mapper {
    public static TransacaoDTO toDTO(TransacaoRequest transacao) {
        DescricaoPagamentoRequest descricao = transacao.getDescricao();
        FormaPagamentoRequest formaPagamento = transacao.getFormaPagamento();

        return TransacaoDTO.builder()
                .id(transacao.getId())
                .cartao(transacao.getCartao())
                .valor(new BigDecimal(descricao.getValor()))
                .estabelecimento(descricao.getEstabelecimento())
                .dataHora(descricao.getDataHora())
                .nsu(descricao.getNsu())
                .codigoAutorizacao(descricao.getCodigoAutorizacao())
                .status(descricao.getStatus().name())
                .tipoPagamento(formaPagamento.tipoPagamento().getCodigo())
                .parcelas(formaPagamento.parcelas())
                .build();
    }

    public static TransacaoRequest toRequest(TransacaoDTO dto) {
        return TransacaoRequest.builder()
                .id(dto.id())
                .cartao(dto.cartao())
                .descricao(
                        DescricaoPagamentoRequest.builder()
                                .valor(dto.valor().toString())
                                .dataHora(dto.dataHora())
                                .estabelecimento(dto.estabelecimento())
                                .nsu(dto.nsu())
                                .codigoAutorizacao(dto.codigoAutorizacao())
                                .status(TipoStatus.valueOf(dto.status()))
                                .build())
                .formaPagamento(
                        FormaPagamentoRequest.builder()
                                .tipoPagamento(TipoPagamento.fromCodigo(dto.tipoPagamento()))
                                .parcelas(dto.parcelas())
                                .build())
                .build();
    }
}
