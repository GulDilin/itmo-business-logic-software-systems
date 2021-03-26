package guldilin.service;

import java.util.List;

import guldilin.dto.WorkerRoleDTO;


public interface WorkerRoleService {
    public List<WorkerRoleDTO> getAll(String title, Integer level);

    public WorkerRoleDTO get(Integer id);

    public WorkerRoleDTO create(WorkerRoleDTO workerRoleDTO);
}
