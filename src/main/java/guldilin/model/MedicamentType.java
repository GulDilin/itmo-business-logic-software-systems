package guldilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
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

}