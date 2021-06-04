package guldilin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MedicamentVendorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    private Medicament medicament;

    @OneToOne(fetch = FetchType.LAZY)
    private Vendor vendor;
}
