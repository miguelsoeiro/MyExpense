package pt.ulusofona.es.num_aluno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pt.ulusofona.es.num_aluno.data.Utilizador;
import pt.ulusofona.es.num_aluno.form.uploadForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.SimpleDateFormat;

/**
 * Created by Miguel Soeiro on 15/01/2017.
 */
@Controller
@Transactional
public class UploadController {
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public  String getFileUploadForm(ModelMap model){
        model.put("uploadForm", new uploadForm());
        return "uploadForm";
    }

    public static final String UPLOAD_FOLDER = "upload";

    //Criar e ter a certeza que existe a pasta upload

    static {
        new File(UPLOAD_FOLDER).mkdir();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public  String handleFileUpload(@Valid @ModelAttribute("uploadForm") uploadForm uploadForm,
                                    BindingResult bindingResult,
                                    @RequestParam("file") MultipartFile file,
                                    ModelMap model, Principal user){

        if (bindingResult.hasErrors()){
            return "uploadForm";
        }

        // Falta o que esta no pdf 5
        if(!file.isEmpty()){
            try{
                String fileName = UPLOAD_FOLDER + "/" + System.currentTimeMillis() + "-" + file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileName));
                stream.write(file.getBytes());
                stream.close();

                String line = "";
                String COMMA_DELIMITER= ",";
                BufferedReader fileReader = null;
                fileReader = new BufferedReader(new FileReader(file.getOriginalFilename()));
                //SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                while((line = fileReader.readLine()) != null){

                    String[] ler = line.split(COMMA_DELIMITER);
                    ler[2].replace(",","");
                    BigDecimal bdvalue = new BigDecimal(ler[2]);
                    if (ler.length > 0){
                        Utilizador utilizador = new Utilizador();
                        utilizador.setDescription(ler[1]);
                        utilizador.setValue(bdvalue);
                        utilizador.setCategory("indifinido"); //tipo de despesa ("indifinido")
                        utilizador.setUtilizador(user.getName());
                        utilizador.setDate(ler[4]);

                        System.out.println("id: " + ler[0] + "descrição: " + ler[1] + "valor: " + bdvalue + "localização: " + ler[3]);
                        System.out.println("id: " + utilizador.getId() + "descrição: " + utilizador.getDescription() + "valor: " + utilizador.getValue() + "localização: " + utilizador.getLocal());
                        em.persist(utilizador);
                    }
                }

                model.put("message", "Sucesso. O Ficheiro " + file.getOriginalFilename() + " foi guardado com sucesso!");
                return "uploadResult";
            }catch (Exception e){
                model.put("message", "Falha no upload => " + e.getMessage());
                return "uploadResult";
            }
        }else {
            model.put("message", "Ficheio Vazio");
            return "uploadResult";
        }
    }
}
