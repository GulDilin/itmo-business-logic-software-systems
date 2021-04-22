package guldilin.repository;

import guldilin.model.MedicamentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentTypeRepository extends JpaRepository<MedicamentType, Long> {
    List<MedicamentType> findAllByTitle(String title);
    List<MedicamentType> findAllByDescription(String description);
}
