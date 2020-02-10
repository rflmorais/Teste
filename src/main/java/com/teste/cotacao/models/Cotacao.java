package com.teste.cotacao.models;

import com.teste.cotacao.enumerator.TipoPrioridade;
import com.teste.cotacao.enumerator.TipoTransporteEnum;
import org.jetbrains.annotations.NotNull;

public class Cotacao {

    private Double valor;
    private Transportadora transportadora;
    private String origem;
    private String destino;
    private TipoPrioridade prioridade;
    private TipoTransporteEnum tipoTransporte;
    private Double tempo;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

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

    public TipoPrioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(TipoPrioridade prioridade) {
        this.prioridade = prioridade;
    }

    public TipoTransporteEnum getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporteEnum tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public boolean equals(Object o, @NotNull TipoPrioridade tipo) {
        switch (tipo) {
            case PRECO:
                if (this.getValor().equals(((Cotacao) o).getValor())) {
                    return true;
                }
                break;
            case TEMPO:
                if (this.getTempo().equals(((Cotacao) o).getTempo())) {
                    return true;
                }
            break;
        }
        return false;
    }

}
