package guldilin.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MedicamentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> medicamentList = new ArrayList<>();


}