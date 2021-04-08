package guldilin.dto;

import guldilin.model.*;

import javax.validation.constraints.NotNull;


public class MedicamentDTO {

    private Integer id;

    @NotNull
    private String title;

    private Integer group;

    private Integer formula;

    private Integer medicamentClass;

    public MedicamentDTO() {}

    public MedicamentDTO(Medicament medicament) {
        this.id = medicament.getId();
        this.title = medicament.getTitle();
        this.group = medicament.getGroup().getId();
        this.formula = medicament.getFormula().getId();
        this.medicamentClass = medicament.getMedicamentClass().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getFormula() {
        return formula;
    }

    public void setFormula(Integer formula) {
        this.formula = formula;
    }

    public Integer getMedicamentClass() {
        return medicamentClass;
    }

    public void setMedicamentClass(Integer medicamentClass) {
        this.medicamentClass = medicamentClass;
    }
}
