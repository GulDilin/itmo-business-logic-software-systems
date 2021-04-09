package guldilin.service;

import guldilin.dto.ProcessApproveDTO;

import java.util.Date;
import java.util.List;

public interface ProcessApproveService {
    List<ProcessApproveDTO> getAll(Long processId, Integer level, Long workerById, Long workerToId);
    ProcessApproveDTO get(Long id);
    ProcessApproveDTO create(ProcessApproveDTO processApproveDTO);
    ProcessApproveDTO update(ProcessApproveDTO processApproveDTO);
}
