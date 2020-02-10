package com.teste.cotacao.enumerator;

public enum TipoPrioridade {

    PRECO("PRECO"), TEMPO("TEMPO");

    private String descricao;

    TipoPrioridade(String desc) {
        this.descricao = desc;
    }

}
