package com.teste.cotacao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.teste.cotacao.enumerator.TipoTransporteEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TransportadoraTipoTransporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "idtransportadora")
    private Transportadora transportadora;

    @Column(name = "tipotransporte")
    private TipoTransporteEnum tipo;

    private Double precoKM;

    private Double tempoKM;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Double getPrecoKM() {
        return precoKM;
    }

    public void setPrecoKM(Double valor) {
        this.precoKM = valor;
    }

    public Double getTempoKM() {
        return tempoKM;
    }

    public void setTempoKM(Double tempoKM) {
        this.tempoKM = tempoKM;
    }

    public TipoTransporteEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporteEnum tipo) {
        this.tipo = tipo;
    }
}
