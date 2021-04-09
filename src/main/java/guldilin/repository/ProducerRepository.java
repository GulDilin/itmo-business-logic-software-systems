package guldilin.repository;

import guldilin.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    List<Producer> findAllByTitle(String title);
    List<Producer> findAllByAddress(String address);
    List<Producer> findAllByContact(String contact);
}
