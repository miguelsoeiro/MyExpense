package pt.ulusofona.es.num_aluno.form;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class uploadForm {
    @NotEmpty(message="O nome tem que estar preenchido")
    @Size(max = 10, message="O nome tem que ter menos de 10 caracteres")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
