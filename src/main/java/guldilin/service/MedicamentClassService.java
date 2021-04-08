package guldilin.service;

import guldilin.dto.MedicamentClassDTO;

import java.util.List;

public interface MedicamentClassService {
    public List<MedicamentClassDTO> getAll(String title, String description);

    public MedicamentClassDTO get(Integer id);

    public MedicamentClassDTO create(MedicamentClassDTO medicamentClassDTO);
}
