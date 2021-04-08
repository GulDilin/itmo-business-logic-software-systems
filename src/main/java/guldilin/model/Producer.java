package guldilin.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String address;

    private String contact;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Collection<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Collection<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
