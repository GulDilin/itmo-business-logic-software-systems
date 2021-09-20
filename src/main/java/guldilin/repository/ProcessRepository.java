package guldilin.repository;

import guldilin.model.Medicament;
import guldilin.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findAllByStatus(String status);
    List<Process> findAllByMedicamentId(Long medicamentId);
}
