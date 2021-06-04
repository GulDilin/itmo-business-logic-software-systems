package guldilin.service.interfaces;

import guldilin.dto.*;
import guldilin.model.MedicamentInterract;

import java.util.List;

public interface MedicamentService {
    List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId);
    MedicamentDTO get(Long id);
    MedicamentFormulaDTO getFormula(Long id);
    MedicamentDTO create(MedicamentDTO medicamentDTO);
    MedicamentDTO update(UpdateMedicamentDTO medicamentDTO);
    MedicamentFormulaDTO createFormula(Long id, MedicamentFormulaDTO medicamentFormulaDTO);
//    MedicamentInterractDTO addInteract(Long id);
    List<ProcessDTO> getProcesses(Long id);
    List<ProcessApproveDTO> getProcessesApproves(Long id, Long processId);
}
