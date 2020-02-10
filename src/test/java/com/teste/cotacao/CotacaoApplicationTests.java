package com.teste.cotacao;

import com.teste.cotacao.controller.TransportadoraController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CotacaoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TransportadoraController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testeInformarPostInvalidoParaCotacao() throws Exception {
        mockMvc.perform(post("/transportadoras").contentType(MediaType.APPLICATION_JSON)
                .content("{\"origem\":\"São Paulo\",\"destino\":\"Manaus\",\"distancia\":\"3875\",\"tipoTransporte\":1,\"prioridade\":0}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
