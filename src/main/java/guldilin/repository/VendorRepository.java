package guldilin.repository;

import guldilin.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findAllByTitle(String title);
    List<Vendor> findAllByAddress(String address);
    List<Vendor> findAllByContact(String contact);
}
