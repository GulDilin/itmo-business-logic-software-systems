package guldilin.service.interfaces;

import guldilin.dto.ProcessApproveDTO;
import guldilin.exceptions.NoSuchObject;

import java.util.Date;
import java.util.List;

public interface ProcessApproveService {
    List<ProcessApproveDTO> getAll(String level, Long workerBy, Long workerTo, Date create, Date update);
    ProcessApproveDTO get(Integer id) throws NoSuchObject;
    ProcessApproveDTO create(ProcessApproveDTO processApproveDTO);
    ProcessApproveDTO update(ProcessApproveDTO processApproveDTO) throws NoSuchObject;
}
