package guldilin.repository;

import guldilin.model.MedicamentClass;
import guldilin.model.Process;
import guldilin.model.ProcessApprove;
import guldilin.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProcessApproveRepository extends JpaRepository<ProcessApprove, Long> {
    List<ProcessApprove> findAllByLevel(String level);
    List<ProcessApprove> findAllByWorkerBy(Worker worker);
    List<ProcessApprove> findAllByWorkerById(Long workerId);
    List<ProcessApprove> findAllByWorkerTo(Worker worker);
    List<ProcessApprove> findAllByWorkerToId(Long workerId);
    List<ProcessApprove> findAllByUpdated(Date date);
    List<ProcessApprove> findAllByCreated(Date date);
    List<ProcessApprove> findAllByApproved(Boolean approved);
    List<ProcessApprove> findAllById(Integer id);
    ProcessApprove findById(Integer id);


}
