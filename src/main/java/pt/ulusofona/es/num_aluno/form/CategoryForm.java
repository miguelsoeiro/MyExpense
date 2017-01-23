package pt.ulusofona.es.num_aluno.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Miguel Soeiro on 14/01/2017.
 */
public class CategoryForm {

    private Long id;
    @NotEmpty(message = "Tem de colocar uma categoria")
    private String category;

    private String utilizador;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getUtilizador() {
        return utilizador;
    }
    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }
}
