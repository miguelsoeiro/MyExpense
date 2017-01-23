package pt.ulusofona.es.num_aluno.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet-test.xml")
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestFileUploadController {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {

        // emula um ficheiro que está a ser uploaded cujo conteúdo é "Blabla" e o nome é "test.txt"
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "test.txt", "text/plain",
                        "Blabla".getBytes());

        String expectedMessage = "Sucesso. O ficheiro test.txt foi guardado com sucesso!";
        /*
        this.mvc.perform(fileUpload("/upload")
                .file(multipartFile)
                .param("name", "Um nome"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", expectedMessage));
        */
        // agora devia-se fazer um pedido GET a alguma funcionalidade que permitisse
        // perceber se o ficheiro tinha sido bem processado...
    }
}
