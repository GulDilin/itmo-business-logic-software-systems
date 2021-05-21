package guldilin.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MedicamentInterract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicament medicament1;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medicament medicament2;

    private String description;

    private Boolean canInterract;
}