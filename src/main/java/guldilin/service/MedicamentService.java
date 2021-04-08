package guldilin.service;

import guldilin.dto.MedicamentDTO;

import java.util.List;

public interface MedicamentService {
    public List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId);

    public MedicamentDTO get(Integer id);

    public MedicamentDTO create(MedicamentDTO medicamentDTO);

}
