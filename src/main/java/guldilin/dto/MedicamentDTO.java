package guldilin.dto;

import guldilin.model.*;

import javax.validation.constraints.NotNull;


public class MedicamentDTO {

    private Integer id;

    @NotNull
    private String title;

    private MedicamentGroup group;

    private MedicamentFormula formula;

    private MedicamentClass medicamentClass;

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

    public MedicamentGroup getGroup() {
        return group;
    }

    public void setGroup(MedicamentGroup group) {
        this.group = group;
    }

    public MedicamentFormula getFormula() {
        return formula;
    }

    public void setFormula(MedicamentFormula formula) {
        this.formula = formula;
    }

    public MedicamentClass getMedicamentClass() {
        return medicamentClass;
    }

    public void setMedicamentClass(MedicamentClass medicamentClass) {
        this.medicamentClass = medicamentClass;
    }
}
