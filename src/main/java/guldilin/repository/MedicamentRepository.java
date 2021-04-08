package guldilin.repository;

import guldilin.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    List<Medicament> findAllByTitle(String title);
    List<Medicament> findAllByActiveSubstances(ActiveSubstance activeSubstance);
    List<Medicament> findAllByActiveSubstancesId(Long activeSubstanceId);
    List<Medicament> findAllByFormula (MedicamentFormula formula);
    List<Medicament> findAllByFormulaId (Long formulaId);
    List<Medicament> findAllByGroup (MedicamentGroup group);
    List<Medicament> findAllByGroupId (Long groupId);

}
