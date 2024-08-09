package alonso.pedro.toolschallenge.model.request;

import alonso.pedro.toolschallenge.model.TipoStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DescricaoPagamentoRequest {
    private String valor;
    private LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private TipoStatus status;
}
