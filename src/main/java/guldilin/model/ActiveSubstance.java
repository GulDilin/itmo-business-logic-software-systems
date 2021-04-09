package guldilin.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ActiveSubstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> medicaments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Collection<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
