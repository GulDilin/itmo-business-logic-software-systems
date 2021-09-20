package guldilin.service.interfaces;

import java.util.List;

import guldilin.dto.WorkerRoleDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;


public interface WorkerRoleService {
    List<WorkerRoleDTO> getAll(String title, Integer level);
    WorkerRoleDTO get(Integer id) throws NoSuchObject;
    WorkerRoleDTO create(WorkerRoleDTO workerRoleDTO) throws ObjectAlreadyExists;
}
