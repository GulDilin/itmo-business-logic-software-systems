package guldilin.repository;

import guldilin.model.Worker;
import guldilin.model.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findAllByRoleLevel(Integer level);

    List<Worker> findAllByRoleTitle(String title);

    List<Worker> findAllByRole(WorkerRole role);
}
