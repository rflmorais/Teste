package com.teste.cotacao.repository;

import com.teste.cotacao.enumerator.TipoIntegracaoEnum;
import com.teste.cotacao.models.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    @Query("SELECT t FROM Transportadora t WHERE t.tipoIntegracao = :tipoIntegracao")
    public List<Transportadora> findByTipo(@Param("tipoIntegracao") TipoIntegracaoEnum tipoIntegracao);

}
