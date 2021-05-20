package guldilin.dto;

import guldilin.model.MedicamentFormula;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MedicamentFormulaDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String text;

    private String description;

    public MedicamentFormulaDTO(MedicamentFormula medicamentFormula) {
        this.title = medicamentFormula.getTitle();
        this.text = medicamentFormula.getText();
        this.description = medicamentFormula.getDescription();
    }
}
