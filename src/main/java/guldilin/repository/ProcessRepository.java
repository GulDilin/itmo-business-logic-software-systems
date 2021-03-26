package guldilin.repository;

import guldilin.model.Medicament;
import guldilin.model.MedicamentFormula;
import guldilin.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findAllByStatus(String status);
    List<Process> findAllByMedicament(Medicament medicament);
}
