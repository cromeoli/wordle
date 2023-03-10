package controller;

import christian.wordle.wordgames.DTO.NuevoJugadorDTO;
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
public class JugadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllPlayers() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/jugadores/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void TestGetOnePlayer() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/jugadores/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}
