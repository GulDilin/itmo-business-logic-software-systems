package guldilin.service;

import guldilin.dto.MedicamentClassDTO;
import guldilin.dto.MedicamentDTO;
import guldilin.dto.UpdateMedicamentDTO;

import java.util.List;

public interface MedicamentService {
    List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId);
    MedicamentDTO get(Integer id);
    MedicamentDTO create(MedicamentDTO medicamentDTO);
    MedicamentDTO update(UpdateMedicamentDTO medicamentDTO);
}
