package com.teste.cotacao.models;

import com.teste.cotacao.enumerator.TipoPrioridade;
import com.teste.cotacao.enumerator.TipoTransporteEnum;

import javax.validation.constraints.NotNull;

public class FreteDados {
    @NotNull
    private String origem;
    @NotNull
    private String destino;
    @NotNull
    private Double distancia;
    private TipoTransporteEnum tipoTransporte;
    @NotNull
    private TipoPrioridade prioridade;

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public TipoTransporteEnum getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporteEnum tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public TipoPrioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(TipoPrioridade prioridade) {
        this.prioridade = prioridade;
    }
}
