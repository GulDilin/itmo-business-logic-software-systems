package guldilin.repository;

import guldilin.model.ProcessApprove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessApproveRepository extends JpaRepository<ProcessApprove, Long> {
    List<ProcessApprove> findAllByLevel(Integer level);
    List<ProcessApprove> findAllByWorkerById(Long workerId);
    List<ProcessApprove> findAllByWorkerToId(Long workerId);
    List<ProcessApprove> findAllByProcessId(Long id);
}
