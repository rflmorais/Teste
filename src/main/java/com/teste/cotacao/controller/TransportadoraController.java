package com.teste.cotacao.controller;

import com.teste.cotacao.domain.CotacaoFrete;
import com.teste.cotacao.enumerator.TipoIntegracaoEnum;
import com.teste.cotacao.models.Cotacao;
import com.teste.cotacao.repository.TransportadoraRepository;
import com.teste.cotacao.models.FreteDados;
import com.teste.cotacao.models.Transportadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraRepository _repo;

    @GetMapping
    private List<Transportadora> getAllDatabase() {
        return _repo.findByTipo(TipoIntegracaoEnum.DATABASE);
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    private List<Transportadora> getAllREST() {
        return _repo.findByTipo(TipoIntegracaoEnum.REST);
    }

    @PostMapping
    private List<Cotacao> getBestPrice(@Valid @RequestBody FreteDados dados) {
        return new CotacaoFrete(_repo).getBestPrice(dados);
    }

}
