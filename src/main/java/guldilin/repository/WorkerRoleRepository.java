package guldilin.repository;

import guldilin.model.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRoleRepository extends JpaRepository<WorkerRole, Long> {
    Optional<WorkerRole> findByTitle(String title);

    List<WorkerRole> findAllByLevel(Integer level);
}
