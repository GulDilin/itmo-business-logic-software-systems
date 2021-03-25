package guldilin.dto;

import guldilin.model.Medicament;

import javax.validation.constraints.NotNull;

public class MedicamentInterractDTO {

    private Integer id;

    @NotNull
    private Medicament medicament1;

    @NotNull
    private Medicament medicament2;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Medicament getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(Medicament medicament1) {
        this.medicament1 = medicament1;
    }

    public Medicament getMedicament2() {
        return medicament2;
    }

    public void setMedicament2(Medicament medicament2) {
        this.medicament2 = medicament2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
