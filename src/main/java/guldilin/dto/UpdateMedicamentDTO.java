package guldilin.dto;

import guldilin.model.Medicament;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateMedicamentDTO extends MedicamentDTO{
    private String title;

    public UpdateMedicamentDTO(Medicament medicament) {
        super(medicament);
    }
}
