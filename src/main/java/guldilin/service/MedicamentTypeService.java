package guldilin.service;

import guldilin.dto.MedicamentTypeDTO;

import java.util.List;

public interface MedicamentTypeService {
    List<MedicamentTypeDTO> getAll(String title, String description);
    MedicamentTypeDTO get(Integer id);
    MedicamentTypeDTO create(MedicamentTypeDTO medicamentTypeDTO);
}
