package com.teste.cotacao.enumerator;

public enum TipoTransporteEnum {

    TERRESTRE("TERRESTRE"), AEREO("AÉREO");

    private String descricao;

    TipoTransporteEnum(String desc) {
        this.descricao = desc;
    }

}

