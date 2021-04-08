package guldilin.service;

import guldilin.dto.MedicamentFormulaDTO;

import java.util.List;

public interface MedicamentFormulaService {
    List<MedicamentFormulaDTO> getAll(String title, String description);

    MedicamentFormulaDTO get(Integer id);

    MedicamentFormulaDTO create(MedicamentFormulaDTO MedicamentFormulaDTO);
}
