package guldilin.dto;

import guldilin.model.MedicamentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MedicamentTypeDTO implements Serializable {

    private Long id;

    private Long parentClass;

    @NotBlank(message = "Title cannot be null")
    private String title;

    private String description;

    public MedicamentTypeDTO(MedicamentType medicamentType) {
        this.id = medicamentType.getId();
        if (medicamentType.getParentClass() != null) {
            this.parentClass = medicamentType.getParentClass().getId();
        }
        this.title = medicamentType.getTitle();
        this.description = medicamentType.getDescription();
    }
}
