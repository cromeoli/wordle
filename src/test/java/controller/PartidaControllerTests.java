package controller;

import christian.wordle.wordgames.DTO.NuevaPartidaDTO;
import christian.wordle.wordgames.WordleApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = WordleApplication.class)
public class PartidaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllPlayers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/partidas/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestGetOnePlayer() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/partidas/16"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteMatchWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/partidas/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRegisterMatchWithInvalidRequestBody() throws Exception {
        // Crea una nueva partida sin un cuerpo de solicitud válido
        String invalidRequestBody = "{\"foo\": \"bar\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/partidas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
