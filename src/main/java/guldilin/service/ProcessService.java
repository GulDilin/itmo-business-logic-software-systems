package guldilin.service;

import guldilin.dto.ProcessDTO;

import java.util.List;

public interface ProcessService {
    public List<ProcessDTO> getAll(String status, Long medicamentId);

    public ProcessDTO get(Integer id);

    public ProcessDTO create(ProcessDTO processRoleDTO);
}
