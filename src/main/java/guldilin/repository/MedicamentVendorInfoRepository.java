package guldilin.repository;

import guldilin.model.MedicamentVendorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentVendorInfoRepository extends JpaRepository<MedicamentVendorInfo, Long> {
    Optional<MedicamentVendorInfo> findByMedicamentId(Long id);
}
