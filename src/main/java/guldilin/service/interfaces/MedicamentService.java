package guldilin.service.interfaces;

import guldilin.dto.*;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.MedicamentInterract;

import java.util.List;

public interface MedicamentService {
    List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId);
    MedicamentDTO get(Long id);
    MedicamentFormulaDTO getFormula(Long id) throws NoSuchObject;
    MedicamentDTO create(MedicamentDTO medicamentDTO) throws NoSuchObject;
    MedicamentDTO update(UpdateMedicamentDTO medicamentDTO) throws NoSuchObject;
    MedicamentFormulaDTO createFormula(Long id, MedicamentFormulaDTO medicamentFormulaDTO) throws NoSuchObject;
    List<ProcessDTO> getProcesses(Long id) throws NoSuchObject;
    List<ProcessApproveDTO> getProcessesApproves(Long id, Long processId) throws NoSuchObject;
}
