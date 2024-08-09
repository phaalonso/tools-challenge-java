package alonso.pedro.toolschallenge.model.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransacaoDTO(
        String id,
        String cartao,
        BigDecimal valor,
        String estabelecimento,
        LocalDateTime dataHora,
        String nsu,
        String codigoAutorizacao,
        String status,
        Integer tipoPagamento,
        Integer parcelas
) {

}
