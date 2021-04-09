package guldilin.service;

import guldilin.dto.ProcessApproveDTO;

import java.util.Date;
import java.util.List;

public interface ProcessApproveService {
    List<ProcessApproveDTO> getAll(String level, Long workerBy, Long workerTo, Date create, Date update);
    ProcessApproveDTO get(Integer id);
    ProcessApproveDTO create(ProcessApproveDTO processApproveDTO);
    ProcessApproveDTO update(ProcessApproveDTO processApproveDTO);
}
