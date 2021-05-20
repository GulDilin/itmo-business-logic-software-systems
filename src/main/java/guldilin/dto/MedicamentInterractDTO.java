package guldilin.dto;

import javax.validation.constraints.NotBlank;

import guldilin.model.MedicamentInterract;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MedicamentInterractDTO implements Serializable {

    private Long id;

    @NotNull
    private Long medicament1;

    @NotNull
    private Long medicament2;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    public MedicamentInterractDTO(MedicamentInterract medicamentInterract) {
        this.id = medicamentInterract.getId();
        if (medicamentInterract.getMedicament1() != null) {
            this.medicament1 = medicamentInterract.getMedicament1().getId();
        }
        if (medicamentInterract.getMedicament2() != null) {
            this.medicament2 = medicamentInterract.getMedicament2().getId();
        }
        this.description = medicamentInterract.getDescription();
    }
}
