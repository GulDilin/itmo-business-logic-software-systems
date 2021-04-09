package guldilin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class MedicamentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Medicament> getMedicamentList() {
        return medicamentList;
    }

    public void setMedicamentList(Collection<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }
}