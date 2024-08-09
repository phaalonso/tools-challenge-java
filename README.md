# Tools Java Challenge

- Está sendo utilizado o pacote `spring-boot-docker-compose` para facilitar configuração do projeto
  - containers estão definidos no `compose.yaml`, ao iniicar o spring, o plugin já deve iniciar os containers junto

Requerimentos:
- [x] Cadastro de uma transação
- [x] Estorno de uma transação
- [x] Consulta
- [ ] Validação dos campos
- [ ] Implementação de testes
    - [x] Testcontainers para realização de teste integrado
    - [x] Criação de uma nova transação
    - [x] Não permite duplicar a transação
    - [ ] Endpoint de consulta
    - [ ] Validar todos os campos


- Transação 
  - ID unico
  - status 
    - AUTORIZADO (pagamento)
    - CANCELADO (estorno)
  - forma pagamento "AVISTA" / "PAGAMENTO LOJA" / "PARCELADO EMISSOR"


```http request
### Cria transação
POST http://localhost:8080/transacao
Content-Type: application/json

{
  "cartao": "************8321",
  "id": "134569213",
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

<> Response
{
  "cartao": "************8321",
  "id": "1345692113",
  "descricao": {
    "valor": "100.00",
    "dataHora": "06/08/2024 23:30:00",
    "estabelecimento": "Posto Aldo",
    "nsu": "123456789",
    "codigoAutorizacao": "123102983",
    "status": "AUTORIZADO"
  },
  "formaPagamento": {
    "tipoPagamento": "AVISTA",
    "parcelas": 1
  }
}

### Consulta transaçao
GET http://localhost:8080/transacao/1345692113

<> Response
{
  "cartao": "************8321",
  "id": "1345692113",
  "descricao": {
    "valor": "100.00",
    "dataHora": "06/08/2024 23:30:00",
    "estabelecimento": "Posto Aldo",
    "nsu": "123456789",
    "codigoAutorizacao": "123102983",
    "status": "AUTORIZADO"
  },
  "formaPagamento": {
    "tipoPagamento": "AVISTA",
    "parcelas": 1
  }
}

### Realiza estorno
POST http://localhost:8080/transacao/estornar/1345692113

<> Response
{
  "cartao": "************8321",
  "id": "1345692113",
  "descricao": {
    "valor": "100.00",
    "dataHora": "06/08/2024 23:30:00",
    "estabelecimento": "Posto Aldo",
    "nsu": "123456789",
    "codigoAutorizacao": "123102983",
    "status": "CANCELADO"
  },
  "formaPagamento": {
    "tipoPagamento": "AVISTA",
    "parcelas": 1
  }
}
```
