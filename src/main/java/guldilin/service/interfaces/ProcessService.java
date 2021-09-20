package guldilin.service.interfaces;

import guldilin.dto.ProcessDTO;
import guldilin.exceptions.NoSuchObject;

import java.util.List;

public interface ProcessService {
    List<ProcessDTO> getAll(String status, Long medicamentId);
    ProcessDTO get(Integer id) throws NoSuchObject;
    ProcessDTO create(ProcessDTO processDTO) throws NoSuchObject;
}
