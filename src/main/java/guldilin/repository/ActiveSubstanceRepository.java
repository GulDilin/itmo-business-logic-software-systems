package guldilin.repository;

import guldilin.model.ActiveSubstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ActiveSubstanceRepository extends JpaRepository<ActiveSubstance, Long> {
    List<ActiveSubstance> findAllByTitle(String title);
    List<ActiveSubstance> findAllByDescription(String description);
}
