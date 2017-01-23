package pt.ulusofona.es.num_aluno.controller;

import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pt.ulusofona.es.num_aluno.data.Agregado;
import pt.ulusofona.es.num_aluno.data.Categorias;
import pt.ulusofona.es.num_aluno.data.Utilizador;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml")
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestFormController {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testListaUtilizadoresVazia() throws Exception {

        List<Utilizador> expectedListaUtilizadores = new ArrayList<Utilizador>();

        mvc.perform(get("/list").principal(new UserPrincipal("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("utilizadores", expectedListaUtilizadores)); // estava como utilizadores
    }
    @Test
    public void testListaCategoriasVazia() throws Exception {

        List<Categorias> expectedListaCategorias = new ArrayList<Categorias>();

        mvc.perform(get("/category").principal(new UserPrincipal("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("category"))
                .andExpect(model().attribute("categorias", expectedListaCategorias));
    }

    /*
    @Test
    public void testInsereUtilizador() throws Exception {

        String description = "teste Descrição";
        String category = "Propinas";
        BigDecimal value = BigDecimal.valueOf(10);
        String local = "CRUZCREDO";
        String date = "10/10/10";
        String utilizador = "admin";
        /*
        // dados do formulário
        String name = "Pedro";
        String address = "Rua das flores";
        Integer telefone = 210456789;

        String expectedMessage = "Sucesso! O utilizador " + utilizador +
                " foi gravado na BD e foi-lhe atribuído o id 1, outras cenas: " +  " DESCRIÇÃO: " + description + " CATEGORIA: " + category +
                " VALOR: " + value + " LOCAL: " + local + " DATA: " + date;

        // POST do formulário
        mvc.perform(post("/form")
                .param("description", description)
                .param("category", category)
                .param("value", value.toString())
                .param("local", local)
                .param("date", date)
                .param("utilizador", utilizador))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("message", expectedMessage));


        // dados do utilizador que vai ser inserido na BD
        Utilizador expectedUtilizador = new Utilizador();
        expectedUtilizador.setId(1);
        expectedUtilizador.setDescription(description);
        expectedUtilizador.setCategory(category);
        expectedUtilizador.setValue(value);
        expectedUtilizador.setLocal(local);
        expectedUtilizador.setDate(date);
        expectedUtilizador.setUtilizador(utilizador);
        List<Utilizador> expectedListaUtilizadores = new ArrayList<Utilizador>();
        expectedListaUtilizadores.add(expectedUtilizador);

        // Obtém lista de utilizadores
        mvc.perform(get("/list").principal(new UserPrincipal("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attribute("utilizadores", expectedListaUtilizadores));

        // Obtém dados do utilizador inserido
        mvc.perform(get("/info/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("info"))
                .andExpect(model().attribute("utilizador", expectedUtilizador));
    }
    */

    /*
    @Test
    public void testListaAgregadosVazia() throws Exception {

        List<Agregado> expectedListaAgregado = new ArrayList<Agregado>();

        mvc.perform(get("/household").principal(new UserPrincipal("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("household"))
                .andExpect(model().attribute("nomes", expectedListaAgregado))
                .andExpect(model().attribute("IDagregado", expectedListaAgregado));
    }
    */
}
