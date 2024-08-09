package alonso.pedro.toolschallenge.model.request;

import alonso.pedro.toolschallenge.model.TipoPagamento;
import lombok.Builder;

@Builder
public record FormaPagamentoRequest(
        TipoPagamento tipoPagamento,
        Integer parcelas
) {

}
