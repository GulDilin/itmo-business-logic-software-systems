package guldilin.service.interfaces;

import guldilin.dto.ProcessDTO;

import java.util.List;

public interface ProcessService {
    List<ProcessDTO> getAll(String status, Long medicamentId);
    ProcessDTO get(Integer id);
    ProcessDTO create(ProcessDTO processDTO);
}
