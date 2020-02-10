package com.teste.cotacao.enumerator;

public enum TipoIntegracaoEnum {

    DATABASE("DATABASE"), REST("REST");

    private String descricao;

    TipoIntegracaoEnum(String desc) {
        this.descricao = desc;
    }

}

