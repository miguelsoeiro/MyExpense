package pt.ulusofona.es.num_aluno.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserForm {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    //DateFormat dm = new SimpleDateFormat("");

    private Long id;
    //private String name;
    //private String address;
    @NotEmpty(
            message = "A descrição tem de estar preenchida."
    )
    @Size(
            min = 0,
            max = 160,
            message = "A descrição tem que ter entre 0 e 160 caracteres"
    )
    private String description;
    // Fazer uma opção no dropdown vazia para forçar utilizador a escolher categoria
    @NotEmpty(message = "Tem de escolher uma categoria")
    private String category;
    @NotNull(
            message = "O valor da despesa tem que estar preenchido"
    )
    @DecimalMin(
            value = "0.01",
            message = "A despesa tem de ser maior que 0.01"
    )
    private BigDecimal value;
    // Unica opcional por isso não tem restrições
    private String local;
    private Date today = Calendar.getInstance().getTime();
    private String date = df.format(today);

    /*public String getName() {
        return name;
    }*/
    /*public String getAddress() {
        return address;
    }*/
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    }
    */
}

