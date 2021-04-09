package guldilin.model;
import guldilin.repository.MedicamentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicamentGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicamentFormula formula;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicamentClass medicamentClass;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> analogs;

    @ManyToMany(fetch = FetchType.LAZY)
    private  Collection<Producer> producers;

    @ManyToMany(fetch = FetchType.LAZY)
    private  Collection<ActiveSubstance> activeSubstances;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Process> processes = new ArrayList<>();

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

    public Collection<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Collection<Process> processes) {
        this.processes = processes;
    }
}