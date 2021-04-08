package guldilin.service;

import guldilin.dto.MedicamentGroupDTO;

import java.util.List;

public interface MedicamentGroupService {
    public List<MedicamentGroupDTO> getAll(String title, String description);

    public MedicamentGroupDTO get(Integer id);

    public MedicamentGroupDTO create(MedicamentGroupDTO MedicamentGroupDTO);
}
