package guldilin.service;

import guldilin.dto.MedicamentDTO;
import guldilin.dto.MedicamentFormulaDTO;
import guldilin.dto.UpdateMedicamentDTO;
import guldilin.model.MedicamentFormula;

import java.util.List;

public interface MedicamentService {
    List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId);
    MedicamentDTO get(Long id);
    MedicamentFormulaDTO getFormula(Long id);
    MedicamentDTO create(MedicamentDTO medicamentDTO);
    MedicamentDTO update(UpdateMedicamentDTO medicamentDTO);
    MedicamentFormulaDTO createFormula(Long id, MedicamentFormulaDTO medicamentFormulaDTO);
}
