package alonso.pedro.toolschallenge.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPagamento {
    AVISTA("AVISTA", 1),
    PAGAMENTO_LOJA("PAGAMENTO LOJA", 2),
    PARCELADO_EMISSOR("PARCELADO EMISSOR", 3);

    @JsonValue
    private final String mensagem;

    private final Integer codigo;

    public static TipoPagamento fromCodigo(Integer codigo){
        for (TipoPagamento tipoPagamento : values()) {
            if (tipoPagamento.codigo.equals(codigo)) {
                return tipoPagamento;
            }
        }

        return null;
    }
}
