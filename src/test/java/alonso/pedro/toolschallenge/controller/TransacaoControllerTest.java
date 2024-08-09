package alonso.pedro.toolschallenge.controller;

import alonso.pedro.toolschallenge.ToolsChallengeApplicationTests;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class TransacaoControllerTest extends ToolsChallengeApplicationTests {
    @Test
    void deveInserirTransacao() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "cartao": "************8321",
                          "id": "1234569213",
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
                        """)
                .when()
                .post("/transacao")
                .then()
                .statusCode(201);
    }

}
