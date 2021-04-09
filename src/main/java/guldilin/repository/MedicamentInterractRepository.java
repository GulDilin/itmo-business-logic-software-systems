package guldilin.repository;

import guldilin.model.MedicamentInterract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentInterractRepository extends JpaRepository<MedicamentInterract, Long> {
    List<MedicamentInterract> findAllByDescription(String description);
}
