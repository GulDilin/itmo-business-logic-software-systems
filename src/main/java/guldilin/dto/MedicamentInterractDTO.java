package guldilin.dto;

import guldilin.model.MedicamentInterract;

import javax.validation.constraints.NotNull;

public class MedicamentInterractDTO {

    private Long id;

    @NotNull
    private Long medicament1;

    @NotNull
    private Long medicament2;

    private String description;

    public MedicamentInterractDTO(MedicamentInterract medicamentInterract) {
        this.id = medicamentInterract.getId();
        this.medicament1 = medicamentInterract.getMedicament1().getId();
        this.medicament2 = medicamentInterract.getMedicament2().getId();
        this.description = medicamentInterract.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(Long medicament1) {
        this.medicament1 = medicament1;
    }

    public Long getMedicament2() {
        return medicament2;
    }

    public void setMedicament2(Long medicament2) {
        this.medicament2 = medicament2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
