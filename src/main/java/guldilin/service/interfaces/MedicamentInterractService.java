package guldilin.service.interfaces;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.exceptions.NoSuchObject;

import java.util.List;

public interface MedicamentInterractService {
    List<MedicamentInterractDTO> getAll(String description);
    MedicamentInterractDTO get(Integer id) throws NoSuchObject;
    MedicamentInterractDTO create(MedicamentInterractDTO medicamentInterractDTO) throws NoSuchObject;
}
