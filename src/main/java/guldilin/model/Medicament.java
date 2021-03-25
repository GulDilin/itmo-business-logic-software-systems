package guldilin.model;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToMany
    private Collection<Medicament> analogs;

    @ManyToMany
    private  Collection<Producer> producers;

    @ManyToMany
    private  Collection<ActiveSubstance> activeSubstances;

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

    public Collection<Medicament> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(Collection<Medicament> analogs) {
        this.analogs = analogs;
    }

    public Collection<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Collection<Producer> producers) {
        this.producers = producers;
    }

    public Collection<ActiveSubstance> getActiveSubstances() {
        return activeSubstances;
    }

    public void setActiveSubstances(Collection<ActiveSubstance> activeSubstances) {
        this.activeSubstances = activeSubstances;
    }
}