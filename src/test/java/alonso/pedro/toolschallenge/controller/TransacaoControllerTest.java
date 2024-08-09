package alonso.pedro.toolschallenge.controller;

import alonso.pedro.toolschallenge.ToolsChallengeApplicationTests;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class TransacaoControllerTest extends ToolsChallengeApplicationTests {
    public static final String REQBODY = """
            {
              "cartao": "************8321",
              "id": "%s",
              "descricao": {
                "valor": "100.00",
                "dataHora": "06/08/2024 23:30:00",
                "estabelecimento": "Posto Aldo"
              },
              "formaPagamento": {
                "tipoPagamento": "AVISTA",
                "parcelas": 1
              }
            }
            """;

    String generateRequestBody(String id) {
        return REQBODY.formatted(id);
    }

    @Test
    void deveInserirTransacao() {
        given()
                .contentType(ContentType.JSON)
                .body(generateRequestBody("1234567"))
                .when()
                .post("/transacao")
                .then()
                .statusCode(201)
                .body("descricao.nsu", equalTo("123456789"))
                .body("descricao.codigoAutorizacao", equalTo("123102983"))
                .body("descricao.status", equalTo("AUTORIZADO"));
    }

    @Test
    void naoDevePermitirDuplicarID() {
        deveInserirTransacao();

        given()
                .contentType(ContentType.JSON)
                .body(generateRequestBody("1234567"))
                .when()
                .post("/transacao")
                .then()
                .statusCode(409);
    }
}
