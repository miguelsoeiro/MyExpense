package pt.ulusofona.es.num_aluno.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Miguel Soeiro on 14/01/2017.
 */

@Entity
public class Categorias implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categorias that = (Categorias) o;

        if (id != that.id) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        return utilizador != null ? utilizador.equals(that.utilizador) : that.utilizador == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (utilizador != null ? utilizador.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String utilizador;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
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
