package guldilin.service;

import guldilin.dto.MedicamentInterractDTO;

import java.util.List;

public interface MedicamentInterractService {
    public List<MedicamentInterractDTO> getAll(String description);

    public MedicamentInterractDTO get(Integer id);

    public MedicamentInterractDTO create(MedicamentInterractDTO medicamentInterractDTO);
}
