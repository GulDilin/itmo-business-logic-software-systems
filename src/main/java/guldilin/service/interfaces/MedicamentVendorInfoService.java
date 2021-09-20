package guldilin.service.interfaces;

import guldilin.dto.MedicamentVendorInfoDTO;
import guldilin.exceptions.NoSuchObject;

public interface MedicamentVendorInfoService {
    MedicamentVendorInfoDTO get(Long medicamentId) throws NoSuchObject;
}
