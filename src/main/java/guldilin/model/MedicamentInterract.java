package guldilin.model;

import javax.persistence.*;

@Entity
public class MedicamentInterract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicament medicament1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicament medicament2;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicament getMedicament1() {
        return medicament1;
    }

    public void setMedicament1(Medicament medicament1) {
        this.medicament1 = medicament1;
    }

    public Medicament getMedicament2() {
        return medicament2;
    }

    public void setMedicament2(Medicament medicament2) {
        this.medicament2 = medicament2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}