package com.teste.cotacao.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teste.cotacao.enumerator.TipoIntegracaoEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Transportadora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @JsonManagedReference
    @OneToMany(mappedBy = "transportadora", targetEntity = TransportadoraTipoTransporte.class, fetch = FetchType.EAGER)
    private List<TransportadoraTipoTransporte> tipoTransporteList;

    @Column(name = "tipointegracao")
    private TipoIntegracaoEnum tipoIntegracao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TransportadoraTipoTransporte> getTipoTransporteList() {
        return tipoTransporteList;
    }

    public void setTipoTransporteList(List<TransportadoraTipoTransporte> tipoTransporteList) {
        this.tipoTransporteList = tipoTransporteList;
    }

    public TipoIntegracaoEnum getTipoIntegracao() {
        return tipoIntegracao;
    }

    public void setTipoIntegracao(TipoIntegracaoEnum tipoIntegracao) {
        this.tipoIntegracao = tipoIntegracao;
    }
}
