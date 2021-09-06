package guldilin.dto;

import guldilin.model.MedicamentVendorInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicamentVendorInfoDTO {
    private Long id;
    private Double price;
    private Long medicament;
    private Long vendor;

    public MedicamentVendorInfoDTO(MedicamentVendorInfo medicamentVendorInfo) {
        this.id = medicamentVendorInfo.getId();
        this.price = medicamentVendorInfo.getPrice();
        this.medicament = medicamentVendorInfo.getMedicament().getId();
        this.vendor = medicamentVendorInfo.getVendor().getId();
    }
}
