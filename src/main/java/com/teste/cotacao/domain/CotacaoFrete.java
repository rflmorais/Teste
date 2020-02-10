package com.teste.cotacao.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.teste.cotacao.enumerator.TipoPrioridade;
import com.teste.cotacao.models.Cotacao;
import com.teste.cotacao.models.FreteDados;
import com.teste.cotacao.enumerator.TipoIntegracaoEnum;
import com.teste.cotacao.models.Transportadora;
import com.teste.cotacao.models.TransportadoraTipoTransporte;
import com.teste.cotacao.repository.TransportadoraRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CotacaoFrete {

    private final TransportadoraRepository _repo;
    private boolean other;

    public CotacaoFrete(TransportadoraRepository _repo) {
        this._repo = _repo;
        this.other = false;
    }

    public List<Cotacao> getBestPrice(FreteDados dados) {

        List<Transportadora> list = new ArrayList<>();
        List<Transportadora> listAux = new ArrayList<>();
        list.addAll(_repo.findByTipo(TipoIntegracaoEnum.DATABASE));
        list.addAll(getExternalREST());

        if (dados.getTipoTransporte() != null) {

            for (Transportadora t : list) {
                for (TransportadoraTipoTransporte ttt : t.getTipoTransporteList()) {
                    if (ttt.getTipo() == dados.getTipoTransporte()) {
                        listAux.add(t);
                    }
                }
            }
        }

        if (!listAux.isEmpty()) {
            list = listAux;
        }

        return getBudget(list, dados);
    }

    private List<Transportadora> getExternalREST() {

        List<Transportadora> list = new ArrayList<>();
        final String uri = "http://localhost:8080/transportadoras/rest";
        RestTemplate restTemplate = new RestTemplate();

        try {
            String result = restTemplate.getForObject(uri, String.class);
            ObjectMapper mapper = new ObjectMapper();

            ObjectReader objectReader = mapper
                    .reader()
                    .forType(new TypeReference<List<Transportadora>>() {
                    });
            list = objectReader.readValue(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<Cotacao> getBudget(List<Transportadora> list, FreteDados dados) {
        List<Cotacao> cotacoes = new ArrayList<>();

        for (Transportadora transportadora : list) {
            for (TransportadoraTipoTransporte tipo : transportadora.getTipoTransporteList()) {
                if (dados.getTipoTransporte() != null
                        && tipo.getTipo() != dados.getTipoTransporte()) {
                    continue;
                }
                cotacoes.add(generateBudget(dados, tipo, transportadora));
            }
        }

        return applyBudgetCriteria(cotacoes, dados.getPrioridade());
    }

    private Cotacao generateBudget(FreteDados dados, TransportadoraTipoTransporte tipo, Transportadora transportadora) {

        if (dados.getTipoTransporte() != null
                && tipo.getTipo() != dados.getTipoTransporte()) {
            return null;
        }

        Cotacao cotacao = new Cotacao();
        cotacao.setOrigem(dados.getOrigem());
        cotacao.setDestino(dados.getDestino());
        cotacao.setPrioridade(dados.getPrioridade());
        cotacao.setTransportadora(transportadora);
        cotacao.setValor((tipo.getPrecoKM() * dados.getDistancia()) / 10);
        cotacao.setTempo((tipo.getTempoKM() * dados.getDistancia()) / 60);
        cotacao.setTipoTransporte(tipo.getTipo());

        return cotacao;
    }

    private List<Cotacao> applyBudgetCriteria(List<Cotacao> cotacoes, TipoPrioridade prioridade) {

        switch (prioridade) {
            case PRECO:
                cotacoes.sort((c1, c2) -> c1.getValor().compareTo(c2.getValor()));
                break;
            case TEMPO:
                cotacoes.sort((c1, c2) -> c1.getTempo().compareTo(c2.getTempo()));
                break;
        }

        List<Cotacao> equalBudgets = new ArrayList<>();
        Cotacao first = cotacoes.get(0);
        cotacoes.remove(first);
        cotacoes.stream().filter(cotacao -> cotacao.equals(first, prioridade)).forEach(cotacao -> equalBudgets.add(cotacao));
        equalBudgets.add(0, first);

        if (!equalBudgets.isEmpty() && !other) {
            other = true;
            TipoPrioridade otherPriority;
            if (prioridade == TipoPrioridade.TEMPO) {
                otherPriority = TipoPrioridade.PRECO;
            } else {
                otherPriority = TipoPrioridade.TEMPO;
            }
            return applyBudgetCriteria(equalBudgets, otherPriority);
        }
        return equalBudgets;
    }


}
