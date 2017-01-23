package pt.ulusofona.es.num_aluno.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.ulusofona.es.num_aluno.data.Agregado;
import pt.ulusofona.es.num_aluno.data.Totais;
import pt.ulusofona.es.num_aluno.data.Utilizador;
import pt.ulusofona.es.num_aluno.data.Variacao;
import pt.ulusofona.es.num_aluno.form.AgregadoForm;
import pt.ulusofona.es.num_aluno.form.UserForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel Soeiro on 13/01/2017.
 */
@Controller
@Transactional
public class AgregadoController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value = "/household", method = RequestMethod.GET)
    public String getHousehold(ModelMap model, Principal utilizador) {
        System.out.println("User em sessão no Agregado: " + utilizador.getName());

        Agregado agregado = new Agregado();

        List<Agregado> Agregado = em.createQuery("SELECT a FROM Agregado a", Agregado.class).getResultList();
        //String user = "users";
        //List<Agregado> users = em.createQuery("SELECT :user b FROM Agregado b", Agregado.class).setParameter("user",user).getResultList();

        List<String> nomes = new ArrayList<String>();

        long IDagregado;

        for (int i =0; i<Agregado.size(); i++){
            if (Agregado.get(i).getUser().equals(utilizador.getName())){
                agregado.setIDagregado(Agregado.get(i).getIDagregado());
                for(int j =0; j<Agregado.size(); j++){
                    if (Agregado.get(i).getIDagregado() == Agregado.get(j).getIDagregado() /*&& Agregado.get(i).getUser() != Agregado.get(j).getUser() */){  // o que esta comentado é para o caso de se querer retirar o user que esta logado da visualização do agregado
                        nomes.add(Agregado.get(j).getUser());
                        //System.out.println("parabens! Ele é do Agregado do: " + Agregado.get(j).getUser());
                    }
                }
            }
        }

        //queryTotalissimoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList();


        model.put("nomes", nomes);
        //model.put("users", users);
        model.put("IDagregado", agregado.getIDagregado());
        return "household";
    }

    @RequestMapping(value = "/household", method = RequestMethod.POST)
    public String postHousehold(@Valid @ModelAttribute("agregadoForm") AgregadoForm agregadoForm, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            return "household";
        }
        long ID;
        List<AgregadoForm> users = em.createQuery("SELECT a FROM Agregado a", AgregadoForm.class).getResultList();
        for (AgregadoForm user: users){
            if (user.getUser().equals(agregadoForm.getUser())){
                ID= user.getId();
                AgregadoForm agregado = em.find(AgregadoForm.class,ID);
                return "rederect:/user/" + ID;
            }
        }
        //List<Agregado> users = em.createQuery("SELECT a FROM Agregado a WHERE user LIKE :user", Agregado.class).setParameter("user", agregadoForm.getUser()).getResultList();
        //System.out.println("isto é o que tu queres ? : " + agregadoForm.getUser());

        //queryTotalissimoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList();

        return "household";
    }

    @RequestMapping(value = "/householdmap", method = RequestMethod.GET)
    public String getHouseholdMap(ModelMap model, Principal user) {
        /*
        List<Utilizador> queryJaneiro = em.createQuery("SELECT u FROM Utilizador u WHERE  date = 01", Utilizador.class).getResultList(),
                queryFevereiro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 02", Utilizador.class).getResultList(),
                queryMarco = em.createQuery("SELECT u FROM Utilizador u WHERE date = 03", Utilizador.class).getResultList(),
                queryAbril = em.createQuery("SELECT u FROM Utilizador u WHERE date = 04", Utilizador.class).getResultList(),
                queryMaio = em.createQuery("SELECT u FROM Utilizador u WHERE date = 05", Utilizador.class).getResultList(),
                queryJunho = em.createQuery("SELECT u FROM Utilizador u WHERE date = 06", Utilizador.class).getResultList(),
                querylJulho = em.createQuery("SELECT u FROM Utilizador u WHERE date = 07", Utilizador.class).getResultList(),
                queryAgosto = em.createQuery("SELECT u FROM Utilizador u WHERE date = 8", Utilizador.class).getResultList(), // Teve de ficar date = 8, porque com 08 = (QuerySyntaxException)
                querySetembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 9", Utilizador.class).getResultList(), // Teve de ficar date = 9, porque com 09 = (QuerySyntaxException)
                queryOutubro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 10", Utilizador.class).getResultList(),
                queryNovembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 11", Utilizador.class).getResultList(),
                queryDezembro = em.createQuery("SELECT u FROM Utilizador u WHERE date = 12", Utilizador.class).getResultList(),
                queryTotalissimo = em.createQuery("SELECT u FROM Utilizador u", Utilizador.class).getResultList();
        */



        Totais totaisJaneiro = new Totais(), totaisFevereiro = new Totais(), totaisMarco = new Totais(), totaisAbril = new Totais(),
                totaisMaio = new Totais(), totaisJunho = new Totais(), totaisJulho  = new Totais(), totaisAgosto= new Totais(),
                totaisSetembro = new Totais(), totaisOutubro = new Totais(),totaisNovembro= new Totais(), totaisDezembro = new Totais(),
                totalissimo = new Totais();

        List<Agregado> Agregado = em.createQuery("SELECT a FROM Agregado a", Agregado.class).getResultList();
        Agregado agregado = new Agregado();


        for (Agregado outrosUserDoAgregado: Agregado){
            if (outrosUserDoAgregado.getUser().equals(user.getName())){
                agregado = em.find(Agregado.class, outrosUserDoAgregado.getId());
            }
        }



        for (Agregado outrosUserDoAgregado: Agregado){
            List<Utilizador> queryJaneiroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 01").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryFevereiroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 02").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryMarcoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 03").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryAbrilAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 04").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryMaioAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 05").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryJunhoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 06").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    querylJulhoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 07").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryAgostoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 8").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    querySetembroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 9").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryOutubroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 10").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryNovembroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 11").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryDezembroAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres and date = 12").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList(),
                    queryTotalissimoAgregado = em.createQuery("select u from Utilizador u where utilizador like :useres").setParameter("useres", outrosUserDoAgregado.getUser()).getResultList();

            if (outrosUserDoAgregado.getIDagregado() == agregado.getIDagregado()){

                /* ### JANEIRO ### */
                for (int i = 0; i<queryJaneiroAgregado.size(); i++){
                    if(queryJaneiroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisJaneiro.somaPropinas(queryJaneiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJaneiroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisJaneiro.somaTransportes(queryJaneiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJaneiroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisJaneiro.somaAlimentacao(queryJaneiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJaneiroAgregado.get(i).getCategory().equals("Renda")){
                        totaisJaneiro.somaReda(queryJaneiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJaneiroAgregado.get(i).getCategory().equals("Outro")){
                        totaisJaneiro.somaOutro(queryJaneiroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisJaneiro.somaTotal();
                }

                /* ### Fevereiro ### */
                for (int i = 0; i<queryFevereiroAgregado.size(); i++){
                    if(queryFevereiroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisFevereiro.somaPropinas(queryFevereiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryFevereiroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisFevereiro.somaTransportes(queryFevereiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryFevereiroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisFevereiro.somaAlimentacao(queryFevereiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryFevereiroAgregado.get(i).getCategory().equals("Renda")){
                        totaisFevereiro.somaReda(queryFevereiroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryFevereiroAgregado.get(i).getCategory().equals("Outro")){
                        totaisFevereiro.somaOutro(queryFevereiroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisFevereiro.somaTotal();
                }

                /* ### Março ### */
                for (int i = 0; i<queryMarcoAgregado.size(); i++){
                    if(queryMarcoAgregado.get(i).getCategory().equals("Propinas")){
                        totaisMarco.somaPropinas(queryMarcoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMarcoAgregado.get(i).getCategory().equals("Transportes")){
                        totaisMarco.somaTransportes(queryMarcoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMarcoAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisMarco.somaAlimentacao(queryMarcoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMarcoAgregado.get(i).getCategory().equals("Renda")){
                        totaisMarco.somaReda(queryMarcoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMarcoAgregado.get(i).getCategory().equals("Outro")){
                        totaisMarco.somaOutro(queryMarcoAgregado.get(i).getValue().doubleValue());
                    }
                    totaisMarco.somaTotal();
                }

                /* ### Abril ### */
                for (int i = 0; i<queryAbrilAgregado.size(); i++){
                    if(queryAbrilAgregado.get(i).getCategory().equals("Propinas")){
                        totaisAbril.somaPropinas(queryAbrilAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAbrilAgregado.get(i).getCategory().equals("Transportes")){
                        totaisAbril.somaTransportes(queryAbrilAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAbrilAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisAbril.somaAlimentacao(queryAbrilAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAbrilAgregado.get(i).getCategory().equals("Renda")){
                        totaisAbril.somaReda(queryAbrilAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAbrilAgregado.get(i).getCategory().equals("Outro")){
                        totaisAbril.somaOutro(queryAbrilAgregado.get(i).getValue().doubleValue());
                    }
                    totaisAbril.somaTotal();
                }

                /* ### Maio ### */
                for (int i = 0; i<queryMaioAgregado.size(); i++){
                    if(queryMaioAgregado.get(i).getCategory().equals("Propinas")){
                        totaisMaio.somaPropinas(queryMaioAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMaioAgregado.get(i).getCategory().equals("Transportes")){
                        totaisMaio.somaTransportes(queryMaioAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMaioAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisMaio.somaAlimentacao(queryMaioAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMaioAgregado.get(i).getCategory().equals("Renda")){
                        totaisMaio.somaReda(queryMaioAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryMaioAgregado.get(i).getCategory().equals("Outro")){
                        totaisMaio.somaOutro(queryMaioAgregado.get(i).getValue().doubleValue());
                    }
                    totaisMaio.somaTotal();
                }

                /* ### Junho ### */
                for (int i = 0; i<queryJunhoAgregado.size(); i++){
                    if(queryJunhoAgregado.get(i).getCategory().equals("Propinas")){
                        totaisJunho.somaPropinas(queryJunhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJunhoAgregado.get(i).getCategory().equals("Transportes")){
                        totaisJunho.somaTransportes(queryJunhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJunhoAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisJunho.somaAlimentacao(queryJunhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJunhoAgregado.get(i).getCategory().equals("Renda")){
                        totaisJunho.somaReda(queryJunhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryJunhoAgregado.get(i).getCategory().equals("Outro")){
                        totaisJunho.somaOutro(queryJunhoAgregado.get(i).getValue().doubleValue());
                    }
                    totaisJunho.somaTotal();
                }

                /* ### Julho ### */
                for (int i = 0; i<querylJulhoAgregado.size(); i++){
                    if(querylJulhoAgregado.get(i).getCategory().equals("Propinas")){
                        totaisJulho.somaPropinas(querylJulhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(querylJulhoAgregado.get(i).getCategory().equals("Transportes")){
                        totaisJulho.somaTransportes(querylJulhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(querylJulhoAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisJulho.somaAlimentacao(querylJulhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(querylJulhoAgregado.get(i).getCategory().equals("Renda")){
                        totaisJulho.somaReda(querylJulhoAgregado.get(i).getValue().doubleValue());
                    }
                    if(querylJulhoAgregado.get(i).getCategory().equals("Outro")){
                        totaisJulho.somaOutro(querylJulhoAgregado.get(i).getValue().doubleValue());
                    }
                    totaisJulho.somaTotal();
                }

                /* ### Agosto ### */
                for (int i = 0; i<queryAgostoAgregado.size(); i++){
                    if(queryAgostoAgregado.get(i).getCategory().equals("Propinas")){
                        totaisAgosto.somaPropinas(queryAgostoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAgostoAgregado.get(i).getCategory().equals("Transportes")){
                        totaisAgosto.somaTransportes(queryAgostoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAgostoAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisAgosto.somaAlimentacao(queryAgostoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAgostoAgregado.get(i).getCategory().equals("Renda")){
                        totaisAgosto.somaReda(queryAgostoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryAgostoAgregado.get(i).getCategory().equals("Outro")){
                        totaisAgosto.somaOutro(queryAgostoAgregado.get(i).getValue().doubleValue());
                    }
                    totaisAgosto.somaTotal();
                }

                /* ### Setembro ### */
                for (int i = 0; i<querySetembroAgregado.size(); i++){
                    if(querySetembroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisSetembro.somaPropinas(querySetembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(querySetembroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisSetembro.somaTransportes(querySetembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(querySetembroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisSetembro.somaAlimentacao(querySetembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(querySetembroAgregado.get(i).getCategory().equals("Renda")){
                        totaisSetembro.somaReda(querySetembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(querySetembroAgregado.get(i).getCategory().equals("Outro")){
                        totaisSetembro.somaOutro(querySetembroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisSetembro.somaTotal();
                }

                /* ### Outubro ### */
                for (int i = 0; i<queryOutubroAgregado.size(); i++){
                    if(queryOutubroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisOutubro.somaPropinas(queryOutubroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryOutubroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisOutubro.somaTransportes(queryOutubroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryOutubroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisOutubro.somaAlimentacao(queryOutubroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryOutubroAgregado.get(i).getCategory().equals("Renda")){
                        totaisOutubro.somaReda(queryOutubroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryOutubroAgregado.get(i).getCategory().equals("Outro")){
                        totaisOutubro.somaOutro(queryOutubroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisOutubro.somaTotal();
                }

                /* ### Novembro ### */
                for (int i = 0; i<queryNovembroAgregado.size(); i++){
                    if(queryNovembroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisNovembro.somaPropinas(queryNovembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryNovembroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisNovembro.somaTransportes(queryNovembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryNovembroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisNovembro.somaAlimentacao(queryNovembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryNovembroAgregado.get(i).getCategory().equals("Renda")){
                        totaisNovembro.somaReda(queryNovembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryNovembroAgregado.get(i).getCategory().equals("Outro")){
                        totaisNovembro.somaOutro(queryNovembroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisNovembro.somaTotal();
                }

                /* ### Dezembro ### */
                for (int i = 0; i<queryDezembroAgregado.size(); i++){
                    if(queryDezembroAgregado.get(i).getCategory().equals("Propinas")){
                        totaisDezembro.somaPropinas(queryDezembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryDezembroAgregado.get(i).getCategory().equals("Transportes")){
                        totaisDezembro.somaTransportes(queryDezembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryDezembroAgregado.get(i).getCategory().equals("Alimentação")){
                        totaisDezembro.somaAlimentacao(queryDezembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryDezembroAgregado.get(i).getCategory().equals("Renda")){
                        totaisDezembro.somaReda(queryDezembroAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryDezembroAgregado.get(i).getCategory().equals("Outro")){
                        totaisDezembro.somaOutro(queryDezembroAgregado.get(i).getValue().doubleValue());
                    }
                    totaisDezembro.somaTotal();
                }

                /* ### Total ### */
                for (int i = 0; i<queryTotalissimoAgregado.size(); i++){
                    if(queryTotalissimoAgregado.get(i).getCategory().equals("Propinas")){
                        totalissimo.somaPropinas(queryTotalissimoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryTotalissimoAgregado.get(i).getCategory().equals("Transportes")){
                        totalissimo.somaTransportes(queryTotalissimoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryTotalissimoAgregado.get(i).getCategory().equals("Alimentação")){
                        totalissimo.somaAlimentacao(queryTotalissimoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryTotalissimoAgregado.get(i).getCategory().equals("Renda")){
                        totalissimo.somaReda(queryTotalissimoAgregado.get(i).getValue().doubleValue());
                    }
                    if(queryTotalissimoAgregado.get(i).getCategory().equals("Outro")){
                        totalissimo.somaOutro(queryTotalissimoAgregado.get(i).getValue().doubleValue());
                    }
                    totalissimo.somaTotal();
                }


            }
        }

        model.put("IDagregado", agregado.getIDagregado());

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


        return "householdmap";
    }

    @RequestMapping(value = "/householdjoin/{id}", method = RequestMethod.GET)
    public String householdjoin(ModelMap model, @PathVariable("id") Long id, Principal user) {

        Agregado agregado = em.find(Agregado.class, id);
        Agregado agregadoJoin = new Agregado();

        List<Agregado> Agregado = em.createQuery("SELECT a FROM Agregado a", Agregado.class).getResultList();

        for (Agregado outroAgregado: Agregado){
            if (outroAgregado.getUser().equals(user.getName())){
                if(outroAgregado.getId() != agregado.getId()){
                    if (outroAgregado.getIDagregado() != agregado.getIDagregado()){
                        outroAgregado.setIDagregado(agregado.getIDagregado());
                    }
                }
            }
        }
        model.addAttribute("message", "Sucesso! Estou nno agregado da pessoa com o ID: " + id + " Mudei para o agregado: " + agregado.getIDagregado() + ".");

        model.put("agregadoJoin", agregadoJoin);

        return "resultHousehold";
    }
}
