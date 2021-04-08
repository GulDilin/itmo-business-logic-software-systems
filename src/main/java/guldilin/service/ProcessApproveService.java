package guldilin.service;

import guldilin.dto.ProcessApproveDTO;

import java.util.Date;
import java.util.List;

public interface ProcessApproveService {
    public List<ProcessApproveDTO> getAll(String level, Long workerBy, Long workerTo, Date create, Date update);

    public ProcessApproveDTO get(Integer id);

    public ProcessApproveDTO create(ProcessApproveDTO processApproveDTO);
}
