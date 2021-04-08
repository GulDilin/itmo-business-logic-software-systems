package guldilin.repository;

import guldilin.model.MedicamentGroup;
import guldilin.model.MedicamentInterract;
import guldilin.model.ProcessApprove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentInterractRepository extends JpaRepository<MedicamentInterract, Long> {
    List<MedicamentInterract> findAllByDescription(String description);
    List<MedicamentInterract> findAllById(Integer id);
    MedicamentInterract findById(Integer id);

}
