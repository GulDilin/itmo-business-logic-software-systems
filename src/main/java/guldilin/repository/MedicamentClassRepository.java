package guldilin.repository;

import guldilin.model.MedicamentClass;
import guldilin.model.MedicamentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentClassRepository extends JpaRepository<MedicamentClass, Long> {
    List<MedicamentClass> findAllByTitle(String title);
    List<MedicamentClass> findAllByDescription(String description);
    List<MedicamentClass> findAllById(Integer id);
    MedicamentClass findById(Integer id);

}
