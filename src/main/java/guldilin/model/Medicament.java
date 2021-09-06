package guldilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
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
    private MedicamentType medicamentType;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Medicament> analogs;

    @ManyToMany(fetch = FetchType.LAZY)
    private  Collection<Vendor> vendors;

    @ManyToMany(fetch = FetchType.LAZY)
    private  Collection<ActiveSubstance> activeSubstances;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Process> processes = new ArrayList<>();

}