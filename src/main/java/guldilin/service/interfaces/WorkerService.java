package guldilin.service.interfaces;

import java.util.List;

import guldilin.dto.WorkerDTO;
import guldilin.dto.WorkerRegistrationRequestDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.Worker;

public interface WorkerService {
    List<WorkerDTO> getAll(String name, Long workerRole, String email, String login, String password);
    WorkerDTO get(Integer id) throws NoSuchObject;
    WorkerDTO create(WorkerRegistrationRequestDTO workerDTO);
    WorkerDTO mapToDTO(Worker worker);
    WorkerDTO findByLoginAndPassword(String login, String password);
}
