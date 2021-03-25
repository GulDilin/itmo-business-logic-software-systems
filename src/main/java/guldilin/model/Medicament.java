package guldilin.model;

import javax.persistence.*;

@Entity
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    private MedicamentGroup group;

    @ManyToOne
    private MedicamentFormula formula;

    @OneToOne
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