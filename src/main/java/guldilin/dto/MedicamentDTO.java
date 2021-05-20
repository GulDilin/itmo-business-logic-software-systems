package guldilin.dto;

import guldilin.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MedicamentDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private Long group;

    private Long medicamentClass;

    public MedicamentDTO(Medicament medicament) {
        this.id = medicament.getId();
        this.title = medicament.getTitle();
        if (medicament.getGroup() != null){
            this.group = medicament.getGroup().getId();
        }
        if (medicament.getMedicamentType() != null) {
            this.medicamentClass = medicament.getMedicamentType().getId();
        }
    }
}
