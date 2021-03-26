package guldilin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import guldilin.model.WorkerRole;

public interface WorkerRoleRepository extends JpaRepository<WorkerRole, Long> {
    List<WorkerRole> findAllByTitle(String title);

    List<WorkerRole> findAllByLevel(Integer level);
}
