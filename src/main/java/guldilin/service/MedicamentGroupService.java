package guldilin.service;

import guldilin.dto.MedicamentGroupDTO;

import java.util.List;

public interface MedicamentGroupService {
    List<MedicamentGroupDTO> getAll(String title, String description);
    MedicamentGroupDTO get(Integer id);
    MedicamentGroupDTO create(MedicamentGroupDTO MedicamentGroupDTO);
}
