package guldilin.dto;

import javax.validation.constraints.NotNull;

public class MedicamentInterractDTO {

    private Long id;

    @NotNull
    private Integer medicament1;

    @NotNull
    private Integer medicament2;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(Integer medicament1) {
        this.medicament1 = medicament1;
    }

    public Integer getMedicament2() {
        return medicament2;
    }

    public void setMedicament2(Integer medicament2) {
        this.medicament2 = medicament2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
