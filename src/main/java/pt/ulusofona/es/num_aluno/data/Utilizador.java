package pt.ulusofona.es.num_aluno.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Utilizador implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilizador that = (Utilizador) o;

        if (id != that.id) return false;
        if (Double.compare(that.soma, soma) != 0) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (local != null ? !local.equals(that.local) : that.local != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return utilizador != null ? utilizador.equals(that.utilizador) : that.utilizador == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (utilizador != null ? utilizador.hashCode() : 0);
        temp = Double.doubleToLongBits(soma);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    @Id
    @GeneratedValue
    private long id;
    /*
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, length = 100)
    private String address;
    */
    @Column(nullable = false, length = 120)
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private BigDecimal value;
    @Column
    private String local;
    //private Date today = Calendar.getInstance().getTime();
    @Column(nullable = false)
    private String date;
    //String date = df.format(today);

    @Column(nullable = false)
    private String utilizador;

    private double soma = 0;
    public long getId() {
        return id;
    }
    /*public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    */

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public BigDecimal getValue() {
        return value;
    }
    public String getLocal() {
        return local;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    /*public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }*/
    public double getSoma() {
        return soma;
    }
    public void setSoma(double soma) {
        this.soma = soma;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }
}


