package guldilin.service;

import java.util.List;

import guldilin.dto.WorkerDTO;

public interface WorkerService {
    List<WorkerDTO> getAll(String name, Long workerRole, String email);
    WorkerDTO get(Integer id);
    WorkerDTO create(WorkerDTO workerDTO);
}
