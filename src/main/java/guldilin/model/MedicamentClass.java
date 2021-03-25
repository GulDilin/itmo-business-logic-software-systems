package guldilin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class MedicamentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private MedicamentGroup parentGroup;

    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> medicamentList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicamentGroup getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(MedicamentGroup parentGroup) {
        this.parentGroup = parentGroup;
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