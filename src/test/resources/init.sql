CREATE TABLE IF NOT EXISTS transacoes
(
    id                 varchar(50) primary key,
    cartao             varchar(50)    not null,
    valor              decimal(15, 2) not null,
    estabelecimento    varchar(100)   not null,
    data_hora          timestamp      not null,
    tipo_pagamento     integer        not null,
    nsu                varchar(50)    not null,
    codigo_autorizacao varchar(50)    not null,
    status             varchar(20)    not null,
    parcelas           integer        not null default 1
);
