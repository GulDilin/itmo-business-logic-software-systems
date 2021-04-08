package guldilin.service;

import guldilin.dto.MedicamentClassDTO;

import java.util.List;

public interface MedicamentClassService {
    List<MedicamentClassDTO> getAll(String title, String description);

    MedicamentClassDTO get(Integer id);

    MedicamentClassDTO create(MedicamentClassDTO medicamentClassDTO);
}
