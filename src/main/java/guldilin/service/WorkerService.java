package guldilin.service;

import java.util.List;

import guldilin.dto.WorkerDTO;

public interface WorkerService {
    public List<WorkerDTO> getAll(String name, Long workerRole, String email);

    public WorkerDTO get(Integer id);

    public WorkerDTO create(WorkerDTO workerDTO);
}
