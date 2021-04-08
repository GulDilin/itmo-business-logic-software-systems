package guldilin.service;

import java.util.List;

import guldilin.dto.WorkerRoleDTO;


public interface WorkerRoleService {
    List<WorkerRoleDTO> getAll(String title, Integer level);

    WorkerRoleDTO get(Integer id);

    WorkerRoleDTO create(WorkerRoleDTO workerRoleDTO);
}
