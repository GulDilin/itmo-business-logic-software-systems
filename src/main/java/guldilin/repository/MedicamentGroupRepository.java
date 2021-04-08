package guldilin.repository;

import guldilin.model.MedicamentFormula;
import guldilin.model.MedicamentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentGroupRepository extends JpaRepository<MedicamentGroup, Long> {
    List<MedicamentGroup> findAllByTitle(String title);
    List<MedicamentGroup> findAllByDescription(String description);
    List<MedicamentGroup> findAllById(Integer id);
    MedicamentGroup findById(Integer id);


}
