package alonso.pedro.toolschallenge.repository;

import alonso.pedro.toolschallenge.model.*;
import alonso.pedro.toolschallenge.model.dto.TransacaoDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Map;
import java.util.Optional;

@Repository
public class TransacaoRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public TransacaoRepository(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    private static final String QUERY_ID = """
            SELECT * FROM transacoes
            where id = :id
            """;

    private static final String QUERY_INSERT = """
            insert into transacoes(id, cartao, valor, estabelecimento, data_hora, tipo_pagamento, nsu, codigo_autorizacao, status, parcelas)
            values (:id, :cartao, :valor, :estabelecimento, :data_hora, :tipo_pagamento, :nsu, :codigo_autorizacao, :status, :parcelas);
            """;

    private static final String QUERY_UPDATE_STATUS = """
            update transacoes
            set status = :status
            where id = :id
            returning *
            """;

    public Optional<TransacaoDTO> findById(String id) {
        var value = namedJdbcTemplate.queryForObject(
                QUERY_ID,
                Map.of("id", id),
                getTransacaoRowMapper());

        return Optional.ofNullable(value);
    }

    public void insere(TransacaoDTO transacao) {
        try {
            var params = new MapSqlParameterSource()
                    .addValue("id", transacao.id(), Types.VARCHAR)
                    .addValue("cartao", transacao.cartao(), Types.VARCHAR)
                    .addValue("valor", transacao.valor(), Types.NUMERIC)
                    .addValue("estabelecimento", transacao.estabelecimento(), Types.VARCHAR)
                    .addValue("data_hora", Timestamp.valueOf(transacao.dataHora()), Types.TIMESTAMP)
                    .addValue("nsu", transacao.nsu(), Types.VARCHAR)
                    .addValue("codigo_autorizacao", transacao.codigoAutorizacao())
                    .addValue("status", transacao.status())
                    .addValue("tipo_pagamento", transacao.tipoPagamento())
                    .addValue("parcelas", transacao.parcelas());

            int rows = namedJdbcTemplate.update(QUERY_INSERT, params);

            if (rows != 1) {
                throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(QUERY_INSERT, 1, rows);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param id     a ser buscado
     * @param status a ser preenchido
     * @return boolean indicado se foi atualizada
     */
    public Optional<TransacaoDTO> atualizaStatus(String id, TipoStatus status) {
        // Se for `Optional.isEmpty()` é porque não atualizou a transação e assim não fornece retorno
        return Optional.ofNullable(namedJdbcTemplate.queryForObject(
                QUERY_UPDATE_STATUS,
                Map.of("status", status.name(), "id", id),
                getTransacaoRowMapper()));
    }

    // Para testes
    public void deleteAll() {
        namedJdbcTemplate.update("DELETE FROM transacoes", new EmptySqlParameterSource());
    }

    private static RowMapper<TransacaoDTO> getTransacaoRowMapper() {
        return (rs, i) -> TransacaoDTO.builder()
                .id(rs.getString("id"))
                .cartao(rs.getString("cartao"))
                .valor(rs.getBigDecimal("valor"))
                .dataHora(rs.getTimestamp("data_hora").toLocalDateTime())
                .estabelecimento(rs.getString("estabelecimento"))
                .nsu(rs.getString("nsu"))
                .codigoAutorizacao(rs.getString("codigo_autorizacao"))
                .status(rs.getString("status"))
                .tipoPagamento(rs.getInt("tipo_pagamento"))
                .parcelas(rs.getInt("parcelas"))
                .build();
    }

}
