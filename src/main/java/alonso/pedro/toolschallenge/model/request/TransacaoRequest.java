package alonso.pedro.toolschallenge.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoRequest {
    private String cartao;
    private String id;
    private DescricaoPagamentoRequest descricao;
    private FormaPagamentoRequest formaPagamento;
}
