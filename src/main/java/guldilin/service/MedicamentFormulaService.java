package guldilin.service;

import guldilin.dto.MedicamentFormulaDTO;

import java.util.List;

public interface MedicamentFormulaService {
    public List<MedicamentFormulaDTO> getAll(String title, String description);

    public MedicamentFormulaDTO get(Integer id);

    public MedicamentFormulaDTO create(MedicamentFormulaDTO MedicamentFormulaDTO);
}
