package pt.ulusofona.es.num_aluno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.ulusofona.es.num_aluno.data.*;
import pt.ulusofona.es.num_aluno.form.CategoryForm;
import pt.ulusofona.es.num_aluno.form.UserForm;
import pt.ulusofona.es.num_aluno.form.uploadForm;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Transactional
public class FormController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAbout() {
        return "about";
    }

    /*
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
                SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                while((line = fileReader.readLine()) != null){

                    String[] ler = line.split(COMMA_DELIMITER);
                    ler[2].replace(",","");
                    BigDecimal bd = new BigDecimal(ler[2]);
                    if (ler.length > 0){
                        Utilizador utilizador = new Utilizador();
                        utilizador.setDescription(ler[1]);
                        utilizador.setValue(bd);
                        //utilizador.set //tipo de despesa ("indifinido")
                        utilizador.setUtilizador(user.getName());
                        utilizador.setDate(ler[4]);

                        System.out.println("id: " + ler[0] + "descrição: " + ler[1] + "valor: " + bd + "localização: " + ler[3]);
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
    */


    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public String getGraph(ModelMap model) {
        List<Utilizador> queryTotalissimo = em.createQuery("SELECT u FROM Utilizador u", Utilizador.class).getResultList();;
        Totais totalissimo = new Totais();

        for (int i =0; i<queryTotalissimo.size(); i++){
            if (queryTotalissimo.get(i).getCategory().equals("Propinas")){
                totalissimo.somaPropinas(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Transportes")){
                totalissimo.somaTransportes(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Alimentação")){
                totalissimo.somaAlimentacao(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Renda")){
                totalissimo.somaReda(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Outro")){
                totalissimo.somaOutro(queryTotalissimo.get(i).getValue().doubleValue());
            }
            totalissimo.somaTotal();
        }

        model.put("totalissimo", totalissimo);

        return "graph";
    }

    @RequestMapping(value = "/brevemente", method = RequestMethod.GET)
    public String getBrevemente() {
        return "brevemente";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(ModelMap model, Principal user) {
        List<Utilizador> utilizadores = em.createQuery("select u from Utilizador u where utilizador like :useres", Utilizador.class).setParameter("useres", user.getName()).getResultList();
        model.put("utilizadores", utilizadores);

        return "list";
    }

    @RequestMapping(value = "/adminlist", method = RequestMethod.GET)
    public String getAdminList(ModelMap model) {
        List<Utilizador> utilizadores = em.createQuery("select u from Utilizador u", Utilizador.class).getResultList();
        model.put("utilizadores", utilizadores);

        return "adminlist";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String getMap(ModelMap model, Principal user) {
        List<Utilizador> queryJaneiro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 01 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryFevereiro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 02 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryMarco = em.createQuery("SELECT u FROM Utilizador u WHERE date = 03 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryAbril = em.createQuery("SELECT u FROM Utilizador u WHERE date = 04 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryMaio = em.createQuery("SELECT u FROM Utilizador u WHERE date = 05 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryJunho = em.createQuery("SELECT u FROM Utilizador u WHERE date = 06 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                querylJulho = em.createQuery("SELECT u FROM Utilizador u WHERE date = 07 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryAgosto = em.createQuery("SELECT u FROM Utilizador u WHERE date = 8 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(), // Teve de ficar date = 8, porque com 08 = (QuerySyntaxException)
                querySetembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 9 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(), // Teve de ficar date = 9, porque com 09 = (QuerySyntaxException)
                queryOutubro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 10 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryNovembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 11 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryDezembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 12 AND utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList(),
                queryTotalissimo = em.createQuery("SELECT u FROM Utilizador u WHERE utilizador like :user", Utilizador.class).setParameter("user", user.getName()).getResultList();

        Totais totaisJaneiro = new Totais(), totaisFevereiro = new Totais(), totaisMarco = new Totais(), totaisAbril = new Totais(),
                totaisMaio = new Totais(), totaisJunho = new Totais(), totaisJulho  = new Totais(), totaisAgosto= new Totais(),
                totaisSetembro = new Totais(), totaisOutubro = new Totais(),totaisNovembro= new Totais(), totaisDezembro = new Totais(),
                totalissimo = new Totais();

        /* ### JANEIRO ### */
        for (int i =0; i<queryJaneiro.size(); i++){
            if (queryJaneiro.get(i).getCategory().equals("Propinas")){
                totaisJaneiro.somaPropinas(queryJaneiro.get(i).getValue().doubleValue());
            }
            if (queryJaneiro.get(i).getCategory().equals("Transportes")){
                totaisJaneiro.somaTransportes(queryJaneiro.get(i).getValue().doubleValue());
            }
            if (queryJaneiro.get(i).getCategory().equals("Alimentação")){
                totaisJaneiro.somaAlimentacao(queryJaneiro.get(i).getValue().doubleValue());
            }
            if (queryJaneiro.get(i).getCategory().equals("Renda")){
                totaisJaneiro.somaReda(queryJaneiro.get(i).getValue().doubleValue());
            }
            if (queryJaneiro.get(i).getCategory().equals("Outro")){
                totaisJaneiro.somaOutro(queryJaneiro.get(i).getValue().doubleValue());
            }
            totaisJaneiro.somaTotal();
        }
        /* ### FEVEREIRO ### */
        for (int i =0; i<queryFevereiro.size(); i++){
            if (queryFevereiro.get(i).getCategory().equals("Propinas")){
                totaisFevereiro.somaPropinas(queryFevereiro.get(i).getValue().doubleValue());
            }
            if (queryFevereiro.get(i).getCategory().equals("Transportes")){
                totaisFevereiro.somaTransportes(queryFevereiro.get(i).getValue().doubleValue());
            }
            if (queryFevereiro.get(i).getCategory().equals("Alimentação")){
                totaisFevereiro.somaAlimentacao(queryFevereiro.get(i).getValue().doubleValue());
            }
            if (queryFevereiro.get(i).getCategory().equals("Renda")){
                totaisFevereiro.somaReda(queryFevereiro.get(i).getValue().doubleValue());
            }
            if (queryFevereiro.get(i).getCategory().equals("Outro")){
                totaisFevereiro.somaOutro(queryFevereiro.get(i).getValue().doubleValue());
            }
            totaisFevereiro.somaTotal();
        }
        /* ### MARÇO ### */
        for (int i =0; i<queryMarco.size(); i++){
            if (queryMarco.get(i).getCategory().equals("Propinas")){
                totaisMarco.somaPropinas(queryMarco.get(i).getValue().doubleValue());
            }
            if (queryMarco.get(i).getCategory().equals("Transportes")){
                totaisMarco.somaTransportes(queryMarco.get(i).getValue().doubleValue());
            }
            if (queryMarco.get(i).getCategory().equals("Alimentação")){
                totaisMarco.somaAlimentacao(queryMarco.get(i).getValue().doubleValue());
            }
            if (queryMarco.get(i).getCategory().equals("Renda")){
                totaisMarco.somaReda(queryMarco.get(i).getValue().doubleValue());
            }
            if (queryMarco.get(i).getCategory().equals("Outro")){
                totaisMarco.somaOutro(queryMarco.get(i).getValue().doubleValue());
            }
            totaisMarco.somaTotal();
        }
        /* ### ABRIL ### */
        for (int i =0; i<queryAbril.size(); i++){
            if (queryAbril.get(i).getCategory().equals("Propinas")){
                totaisAbril.somaPropinas(queryAbril.get(i).getValue().doubleValue());
            }
            if (queryAbril.get(i).getCategory().equals("Transportes")){
                totaisAbril.somaTransportes(queryAbril.get(i).getValue().doubleValue());
            }
            if (queryAbril.get(i).getCategory().equals("Alimentação")){
                totaisAbril.somaAlimentacao(queryAbril.get(i).getValue().doubleValue());
            }
            if (queryAbril.get(i).getCategory().equals("Renda")){
                totaisAbril.somaReda(queryAbril.get(i).getValue().doubleValue());
            }
            if (queryAbril.get(i).getCategory().equals("Outro")){
                totaisAbril.somaOutro(queryAbril.get(i).getValue().doubleValue());
            }
            totaisAbril.somaTotal();
        }
        /* ### MAIO ### */
        for (int i =0; i<queryMaio.size(); i++){
            if (queryMaio.get(i).getCategory().equals("Propinas")){
                totaisMaio.somaPropinas(queryMaio.get(i).getValue().doubleValue());
            }
            if (queryMaio.get(i).getCategory().equals("Transportes")){
                totaisMaio.somaTransportes(queryMaio.get(i).getValue().doubleValue());
            }
            if (queryMaio.get(i).getCategory().equals("Alimentação")){
                totaisMaio.somaAlimentacao(queryMaio.get(i).getValue().doubleValue());
            }
            if (queryMaio.get(i).getCategory().equals("Renda")){
                totaisMaio.somaReda(queryMaio.get(i).getValue().doubleValue());
            }
            if (queryMaio.get(i).getCategory().equals("Outro")){
                totaisMaio.somaOutro(queryMaio.get(i).getValue().doubleValue());
            }
            totaisMaio.somaTotal();
        }
        /* ### JUNHO ### */
        for (int i =0; i<queryJunho.size(); i++){
            if (queryJunho.get(i).getCategory().equals("Propinas")){
                totaisJunho.somaPropinas(queryJunho.get(i).getValue().doubleValue());
            }
            if (queryJunho.get(i).getCategory().equals("Transportes")){
                totaisJunho.somaTransportes(queryJunho.get(i).getValue().doubleValue());
            }
            if (queryJunho.get(i).getCategory().equals("Alimentação")){
                totaisJunho.somaAlimentacao(queryJunho.get(i).getValue().doubleValue());
            }
            if (queryJunho.get(i).getCategory().equals("Renda")){
                totaisJunho.somaReda(queryJunho.get(i).getValue().doubleValue());
            }
            if (queryJunho.get(i).getCategory().equals("Outro")){
                totaisJunho.somaOutro(queryJunho.get(i).getValue().doubleValue());
            }
            totaisJunho.somaTotal();
        }
        /* ### JULHO ### */
        for (int i =0; i<querylJulho.size(); i++){
            if (querylJulho.get(i).getCategory().equals("Propinas")){
                totaisJulho.somaPropinas(querylJulho.get(i).getValue().doubleValue());
            }
            if (querylJulho.get(i).getCategory().equals("Transportes")){
                totaisJulho.somaTransportes(querylJulho.get(i).getValue().doubleValue());
            }
            if (querylJulho.get(i).getCategory().equals("Alimentação")){
                totaisJulho.somaAlimentacao(querylJulho.get(i).getValue().doubleValue());
            }
            if (querylJulho.get(i).getCategory().equals("Renda")){
                totaisJulho.somaReda(querylJulho.get(i).getValue().doubleValue());
            }
            if (querylJulho.get(i).getCategory().equals("Outro")){
                totaisJulho.somaOutro(querylJulho.get(i).getValue().doubleValue());
            }
            totaisJulho.somaTotal();
        }
        /* ### AGOSTO ### */
        for (int i =0; i<queryAgosto.size(); i++){
            if (queryAgosto.get(i).getCategory().equals("Propinas")){
                totaisAgosto.somaPropinas(queryAgosto.get(i).getValue().doubleValue());
            }
            if (queryAgosto.get(i).getCategory().equals("Transportes")){
                totaisAgosto.somaTransportes(queryAgosto.get(i).getValue().doubleValue());
            }
            if (queryAgosto.get(i).getCategory().equals("Alimentação")){
                totaisAgosto.somaAlimentacao(queryAgosto.get(i).getValue().doubleValue());
            }
            if (queryAgosto.get(i).getCategory().equals("Renda")){
                totaisAgosto.somaReda(queryAgosto.get(i).getValue().doubleValue());
            }
            if (queryAgosto.get(i).getCategory().equals("Outro")){
                totaisAgosto.somaOutro(queryAgosto.get(i).getValue().doubleValue());
            }
            totaisAgosto.somaTotal();
        }
        /* ### SETEMBRO ### */
        for (int i =0; i<querySetembro.size(); i++){
            if (querySetembro.get(i).getCategory().equals("Propinas")){
                totaisSetembro.somaPropinas(querySetembro.get(i).getValue().doubleValue());
            }
            if (querySetembro.get(i).getCategory().equals("Transportes")){
                totaisSetembro.somaTransportes(querySetembro.get(i).getValue().doubleValue());
            }
            if (querySetembro.get(i).getCategory().equals("Alimentação")){
                totaisSetembro.somaAlimentacao(querySetembro.get(i).getValue().doubleValue());
            }
            if (querySetembro.get(i).getCategory().equals("Renda")){
                totaisSetembro.somaReda(querySetembro.get(i).getValue().doubleValue());
            }
            if (querySetembro.get(i).getCategory().equals("Outro")){
                totaisSetembro.somaOutro(querySetembro.get(i).getValue().doubleValue());
            }
            totaisSetembro.somaTotal();
        }
        /* ### OUTOBRO ### */
        for (int i =0; i<queryOutubro.size(); i++){
            if (queryOutubro.get(i).getCategory().equals("Propinas")){
                totaisOutubro.somaPropinas(queryOutubro.get(i).getValue().doubleValue());
            }
            if (queryOutubro.get(i).getCategory().equals("Transportes")){
                totaisOutubro.somaTransportes(queryOutubro.get(i).getValue().doubleValue());
            }
            if (queryOutubro.get(i).getCategory().equals("Alimentação")){
                totaisOutubro.somaAlimentacao(queryOutubro.get(i).getValue().doubleValue());
            }
            if (queryOutubro.get(i).getCategory().equals("Renda")){
                totaisOutubro.somaReda(queryOutubro.get(i).getValue().doubleValue());
            }
            if (queryOutubro.get(i).getCategory().equals("Outro")){
                totaisOutubro.somaOutro(queryOutubro.get(i).getValue().doubleValue());
            }
            totaisOutubro.somaTotal();
        }
        /* ### NOVEMBRO ### */
        for (int i =0; i<queryNovembro.size(); i++){
            if (queryNovembro.get(i).getCategory().equals("Propinas")){
                totaisNovembro.somaPropinas(queryNovembro.get(i).getValue().doubleValue());
            }
            if (queryNovembro.get(i).getCategory().equals("Transportes")){
                totaisNovembro.somaTransportes(queryNovembro.get(i).getValue().doubleValue());
            }
            if (queryNovembro.get(i).getCategory().equals("Alimentação")){
                totaisNovembro.somaAlimentacao(queryNovembro.get(i).getValue().doubleValue());
            }
            if (queryNovembro.get(i).getCategory().equals("Renda")){
                totaisNovembro.somaReda(queryNovembro.get(i).getValue().doubleValue());
            }
            if (queryNovembro.get(i).getCategory().equals("Outro")){
                totaisNovembro.somaOutro(queryNovembro.get(i).getValue().doubleValue());
            }
            totaisNovembro.somaTotal();
        }
        /* ### DEZEMBRO ### */
        for (int i =0; i<queryDezembro.size(); i++){
            if (queryDezembro.get(i).getCategory().equals("Propinas")){
                totaisDezembro.somaPropinas(queryDezembro.get(i).getValue().doubleValue());
            }
            if (queryDezembro.get(i).getCategory().equals("Transportes")){
                totaisDezembro.somaTransportes(queryDezembro.get(i).getValue().doubleValue());
            }
            if (queryDezembro.get(i).getCategory().equals("Alimentação")){
                totaisDezembro.somaAlimentacao(queryDezembro.get(i).getValue().doubleValue());
            }
            if (queryDezembro.get(i).getCategory().equals("Renda")){
                totaisDezembro.somaReda(queryDezembro.get(i).getValue().doubleValue());
            }
            if (queryDezembro.get(i).getCategory().equals("Outro")){
                totaisDezembro.somaOutro(queryDezembro.get(i).getValue().doubleValue());
            }
            totaisDezembro.somaTotal();
        }
        /* ### TOTAL ### */
        for (int i =0; i<queryTotalissimo.size(); i++){
            if (queryTotalissimo.get(i).getCategory().equals("Propinas")){
                totalissimo.somaPropinas(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Transportes")){
                totalissimo.somaTransportes(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Alimentação")){
                totalissimo.somaAlimentacao(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Renda")){
                totalissimo.somaReda(queryTotalissimo.get(i).getValue().doubleValue());
            }
            if (queryTotalissimo.get(i).getCategory().equals("Outro")){
                totalissimo.somaOutro(queryTotalissimo.get(i).getValue().doubleValue());
            }
            totalissimo.somaTotal();
        }

        /* ### COLUNA VARIAÇÃO ### */
        Variacao variacaoJaneiroFevereiro = new Variacao();
        Variacao variacaoFevereiroMarco = new Variacao();
        Variacao variacaoMarcoAbril = new Variacao();
        Variacao variacaoAbrilMaio = new Variacao();
        Variacao variacaoMaioJunho = new Variacao();
        Variacao variacaoJunhoJulho = new Variacao();
        Variacao variacaoJulhoAgosto = new Variacao();
        Variacao variacaoAgostoSetembro = new Variacao();
        Variacao variacaoSetembroOutobro = new Variacao();
        Variacao variacaoOutobroNovembro = new Variacao();
        Variacao variacaoNovembroDezembro = new Variacao();


        variacaoJaneiroFevereiro.variacaoCalculo(totaisJaneiro.somaTotal(),totaisFevereiro.somaTotal());
        variacaoFevereiroMarco.variacaoCalculo(totaisFevereiro.somaTotal(),totaisMarco.somaTotal());
        variacaoMarcoAbril.variacaoCalculo(totaisMarco.somaTotal(),totaisAbril.somaTotal());
        variacaoAbrilMaio.variacaoCalculo(totaisAbril.somaTotal(),totaisMaio.somaTotal());
        variacaoMaioJunho.variacaoCalculo(totaisMaio.somaTotal(),totaisJunho.somaTotal());
        variacaoJunhoJulho.variacaoCalculo(totaisJunho.somaTotal(),totaisJulho.somaTotal());
        variacaoJulhoAgosto.variacaoCalculo(totaisJulho.somaTotal(),totaisAgosto.somaTotal());
        variacaoAgostoSetembro.variacaoCalculo(totaisAgosto.somaTotal(),totaisSetembro.somaTotal());
        variacaoSetembroOutobro.variacaoCalculo(totaisSetembro.somaTotal(),totaisOutubro.somaTotal());
        variacaoOutobroNovembro.variacaoCalculo(totaisOutubro.somaTotal(),totaisNovembro.somaTotal());
        variacaoNovembroDezembro.variacaoCalculo(totaisNovembro.somaTotal(),totaisDezembro.somaTotal());

        model.put("variacaoJaneiroFevereiro", variacaoJaneiroFevereiro);
        model.put("variacaoFevereiroMarco", variacaoFevereiroMarco);
        model.put("variacaoMarcoAbril", variacaoMarcoAbril);
        model.put("variacaoAbrilMaio", variacaoAbrilMaio);
        model.put("variacaoMaioJunho", variacaoMaioJunho);
        model.put("variacaoJunhoJulho", variacaoJunhoJulho);
        model.put("variacaoJulhoAgosto", variacaoJulhoAgosto);
        model.put("variacaoAgostoSetembro", variacaoAgostoSetembro);
        model.put("variacaoSetembroOutobro", variacaoSetembroOutobro);
        model.put("variacaoOutobroNovembro", variacaoOutobroNovembro);
        model.put("variacaoNovembroDezembro", variacaoNovembroDezembro);

        model.put("totaisJaneiro", totaisJaneiro);
        model.put("totaisFevereiro", totaisFevereiro);
        model.put("totaisMarco", totaisMarco);
        model.put("totaisAbril", totaisAbril);
        model.put("totaisMaio", totaisMaio);
        model.put("totaisJunho", totaisJunho);
        model.put("totaisJulho", totaisJulho);
        model.put("totaisAgosto", totaisAgosto);
        model.put("totaisSetembro", totaisSetembro);
        model.put("totaisOutubro", totaisOutubro);
        model.put("totaisNovembro", totaisNovembro);
        model.put("totaisDezembro", totaisDezembro);
        model.put("totalissimo", totalissimo);

        return "map";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getForm(ModelMap model) {
        List<Categorias> categorias = em.createQuery("SELECT u FROM Categorias u", Categorias.class).getResultList();
        Categorias categoriass = new Categorias();

        System.out.println("A categorica é: " + categoriass.getCategory());
        model.put("categorias", categorias);

        model.put("userForm", new UserForm());
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@Valid @ModelAttribute("userForm") UserForm userForm,
                             BindingResult bindingResult,
                             ModelMap model, Principal user) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        Utilizador utilizador;
        if (userForm.getId() != null) {
            utilizador = em.find(Utilizador.class, userForm.getId());
        }else {
            utilizador = new Utilizador();
        }

        utilizador.setDescription(userForm.getDescription());
        utilizador.setCategory(userForm.getCategory());
        utilizador.setValue(userForm.getValue());
        utilizador.setLocal(userForm.getLocal());
        utilizador.setDate(userForm.getDate());
        utilizador.setUtilizador(user.getName());
        em.persist(utilizador);

        model.addAttribute("message"," Foi gravado na Base de Dados com SUCESSO e foi-lhe atribuído o id \n" + utilizador.getId() +
                " A descrição é: \n" + utilizador.getDescription() +
                " a Categorias é: \n" + utilizador.getCategory() +
                "o valor é: \n" + utilizador.getValue() +
                "e com a Data: \n" + utilizador.getDate());
        return "result";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String getCategoryForm(ModelMap model) {

        List<Categorias> categorias = em.createQuery("SELECT c FROM Categorias c", Categorias.class).getResultList();
        Categorias categoriass = new Categorias();
        System.out.println("A categorica é: " + categoriass.getCategory());

        model.put("categorias", categorias);
        model.put("categoryForm", new CategoryForm());
        return "category";
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String submitCategoryForm(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm,
                             BindingResult bindingResult,
                             ModelMap model, Principal user) {
        if (bindingResult.hasErrors()) {
            return "category";
        }

        Categorias categorias;
        if (categoryForm.getId() != null) {
            categorias = em.find(Categorias.class, categoryForm.getId());
        }else {
            categorias = new Categorias();
        }

        categorias.setCategory(categoryForm.getCategory());
        categorias.setUtilizador(user.getName());
        em.persist(categorias);

        model.addAttribute("message"," Foi gravado na Base de Dados com SUCESSO e foi-lhe atribuído o id \n" + " A Categorias é: \n" + categorias.getCategory());
        return "resultCategory";
    }


    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String getInfo(ModelMap model, @PathVariable("id") Long id) {

        Utilizador utilizador = em.find(Utilizador.class, id);

        model.put("utilizador", utilizador);

        return "info";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(ModelMap model, @PathVariable("id") Long id, Principal user) {

        List<Categorias> categorias = em.createQuery("SELECT u FROM Categorias u", Categorias.class).getResultList();
        Categorias categoriass = new Categorias();

        model.put("categorias", categorias);

        Utilizador utilizador = em.find(Utilizador.class, id);
        //Categorias categoria = new Categorias();
        UserForm userForm = new UserForm();
        userForm.setId(utilizador.getId());
        //userForm.setName(utilizador.getName());
        //userForm.setAddress(utilizador.getAddress());
        userForm.setDescription(utilizador.getDescription());
        userForm.setCategory(utilizador.getCategory());
        userForm.setValue(utilizador.getValue());
        userForm.setLocal(utilizador.getLocal());
        utilizador.setUtilizador(user.getName());

        model.put("userForm", userForm);

        return "form";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(ModelMap model, @PathVariable("id") Long id) {

        Utilizador utilizador = em.find(Utilizador.class, id);
        em.remove(utilizador);

        model.addAttribute("message", "Sucesso! O utilizador " + id + " foi apagado.");
        return "result";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUser(ModelMap model, @PathVariable("id") Long id) {
        Agregado agregado = em.find(Agregado.class, id);
        model.put("agregado", agregado);

        List<Agregado> users = em.createQuery("SELECT a FROM Agregado a WHERE user LIKE :user", Agregado.class).setParameter("user",agregado.getUser()).getResultList();
        model.put("users", users);

        return "infoAgregado";
    }

    @RequestMapping(value = "/infocategory/{id}", method = RequestMethod.GET)
    public String getInfoCategory(ModelMap model, @PathVariable("id") Long id) {

        Categorias categorias = em.find(Categorias.class, id);

        model.put("categorias", categorias);

        return "infocategory";
    }
    @RequestMapping(value = "/editcategory/{id}", method = RequestMethod.GET)
    public String editCategory(ModelMap model, @PathVariable("id") Long id, Principal user) {

        List<Categorias> categoriass = em.createQuery("SELECT c FROM Categorias c", Categorias.class).getResultList();
        model.put("categorias", categoriass);

        Categorias categorias = em.find(Categorias.class, id);
        CategoryForm categoryForm = new CategoryForm();

        if (categorias.getCategory() != "Transportes" && categorias.getCategory() != "Propinas" && categorias.getId() != 3 && categorias.getId() != 4){
            categoryForm.setId(categorias.getId());
            System.out.println("O QUE TU QUERES: " + categorias.getId());
            categoryForm.setCategory(categorias.getCategory());
            categoryForm.setUtilizador(user.getName());
            em.remove(categorias);

            model.put("categoryForm", categoryForm);
        }else {
            model.addAttribute("message", "Não pode editar esta categoria!");
            return "resultCategory";
        }

        return "category";
    }

    @RequestMapping(value = "/editcategory/{id}", method = RequestMethod.POST)
    public String editCategoryPost(@Valid @ModelAttribute("categoryForm") CategoryForm categoryForm) {

        CategoryForm categoria;
        if (categoryForm.getId() != null){
            categoria = em.find(CategoryForm.class, categoryForm.getId());
        }else {
            categoria = new CategoryForm();
        }
        categoria.setId(categoryForm.getId());

        categoria.setCategory(categoryForm.getCategory());
        em.remove(categoria);
        em.persist(categoria);

        return "category";
    }

}

