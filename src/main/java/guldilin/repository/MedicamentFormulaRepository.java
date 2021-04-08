package guldilin.repository;

import guldilin.model.MedicamentFormula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentFormulaRepository extends JpaRepository<MedicamentFormula, Long> {
    List<MedicamentFormula> findAllByTitle(String title);
    List<MedicamentFormula> findAllByDescription(String description);
}
