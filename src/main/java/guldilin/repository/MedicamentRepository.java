package guldilin.repository;

import guldilin.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    List<Medicament> findAllByTitle(String title);
    List<Medicament> findAllByActiveSubstancesId(Long activeSubstanceId);
    List<Medicament> findAllByFormulaId (Long formulaId);
    List<Medicament> findAllByGroupId (Long groupId);
}
