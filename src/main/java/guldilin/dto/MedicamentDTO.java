package guldilin.dto;

import guldilin.model.*;

import javax.validation.constraints.NotBlank;

public class MedicamentDTO {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private Long group;

    private Long formula;

    private Long medicamentClass;

    public MedicamentDTO() {}

    public MedicamentDTO(Medicament medicament) {
        this.id = medicament.getId();
        this.title = medicament.getTitle();
        this.group = medicament.getGroup().getId();
        this.formula = medicament.getFormula().getId();
        this.medicamentClass = medicament.getMedicamentClass().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getFormula() {
        return formula;
    }

    public void setFormula(Long formula) {
        this.formula = formula;
    }

    public Long getMedicamentClass() {
        return medicamentClass;
    }

    public void setMedicamentClass(Long medicamentClass) {
        this.medicamentClass = medicamentClass;
    }
}
