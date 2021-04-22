package guldilin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class MedicamentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicamentType parentClass;

    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> medicamentList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicamentType getParentClass() {
        return parentClass;
    }

    public void setParentClass(MedicamentType parentClass) {
        this.parentClass = parentClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Medicament> getMedicamentList() {
        return medicamentList;
    }

    public void setMedicamentList(Collection<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }
}