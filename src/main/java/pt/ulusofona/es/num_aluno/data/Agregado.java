package pt.ulusofona.es.num_aluno.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Miguel Soeiro on 13/01/2017.
 */

@Entity
public class Agregado implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agregado agregado = (Agregado) o;

        if (id != agregado.id) return false;
        if (IDagregado != agregado.IDagregado) return false;
        return user != null ? user.equals(agregado.user) : agregado.user == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (int) (IDagregado ^ (IDagregado >>> 32));
        return result;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private long IDagregado;

    public long getId() {
        return id;
    }

    public long getIDagregado() {
        return IDagregado;
    }

    public void setIDagregado(long IDagregado) {
        this.IDagregado = IDagregado;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


}
