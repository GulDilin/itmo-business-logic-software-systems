package guldilin.service;

import guldilin.dto.MedicamentInterractDTO;

import java.util.List;

public interface MedicamentInterractService {
    List<MedicamentInterractDTO> getAll(String description);

    MedicamentInterractDTO get(Integer id);

    MedicamentInterractDTO create(MedicamentInterractDTO medicamentInterractDTO);
}
