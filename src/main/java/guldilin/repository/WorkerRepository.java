package guldilin.repository;

import guldilin.model.Worker;
import guldilin.model.WorkerRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    List<Worker> findAllByEmail(String email);
    List<Worker> findAllByRoleTitle(String title);
    List<Worker> findAllByRoleId(Long id);
    List<Worker> findAllByRole(WorkerRole role);
    List<Worker> findAllByName(String name);
    List<Worker> findAllByLogin(String login);
    Optional<Worker> findByLogin(String login);
}
