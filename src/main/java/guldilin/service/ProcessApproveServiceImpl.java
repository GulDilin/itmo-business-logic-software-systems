package guldilin.service;

import guldilin.dto.ProcessApproveDTO;
import guldilin.model.ProcessApprove;
import guldilin.repository.ProcessApproveRepository;
import guldilin.repository.ProcessRepository;
import guldilin.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessApproveServiceImpl implements ProcessApproveService {
    private final ProcessApproveRepository processApproveRepository;
    private final ProcessRepository processRepository;
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProcessApproveServiceImpl(ProcessApproveRepository processApproveRepository, ProcessRepository processRepository, WorkerRepository workerRepository, ModelMapper modelMapper) {
        this.processApproveRepository = processApproveRepository;
        this.processRepository = processRepository;
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProcessApproveDTO> getAll(Long processId, Integer level, Long workerById, Long workerToId) {
        List<ProcessApprove> ProcessApproveList;

        if (level != null) {
            ProcessApproveList = processApproveRepository.findAllByLevel(level);
        } else if (processId != null) {
            ProcessApproveList = processApproveRepository.findAllByProcessId(processId);
        } else if (workerById != null) {
            ProcessApproveList = processApproveRepository.findAllByWorkerById(workerById);
        } else if (workerToId != null) {
            ProcessApproveList = processApproveRepository.findAllByWorkerToId(workerToId);
        } else{
            ProcessApproveList = processApproveRepository.findAll();
        }
        return ProcessApproveList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProcessApproveDTO get(Long id) {
        Optional<ProcessApprove> found = processApproveRepository.findById(id);
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such process approve");
    }

    @Override
    public ProcessApproveDTO create(ProcessApproveDTO processApproveDTO) {
        processApproveDTO.setId(null);
        Date now = new Date();
        processApproveDTO.setCreated(now);
        processApproveDTO.setUpdated(now);
        try {
            return mapToDTO(processApproveRepository.save(mapToEntity(processApproveDTO)));
        } catch (IllegalArgumentException exp) {
            throw exp;
        } catch (InvalidDataAccessApiUsageException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot save to database");
        }
    }

    @Override
    public ProcessApproveDTO update(ProcessApproveDTO processApproveDTO) {
        processApproveRepository.findById(processApproveDTO.getId())
                .orElseThrow( () -> new IllegalArgumentException("No such process approve") );
        processApproveDTO.setUpdated(new Date());
        return mapToDTO(processApproveRepository.save(mapToEntity(processApproveDTO)));
    }

    private ProcessApproveDTO mapToDTO(ProcessApprove processApprove) {
        return new ProcessApproveDTO(processApprove);
    }

    private ProcessApprove mapToEntity(ProcessApproveDTO processApproveDTO) {

        ProcessApprove processApprove = modelMapper.map(processApproveDTO, ProcessApprove.class);
        if (processApproveDTO.getProcess() != null) {
            processApprove.setProcess(
                    processRepository.findById(processApproveDTO.getProcess())
                            .orElseThrow(() -> new IllegalArgumentException("No such process")));
        }
        if (processApproveDTO.getWorkerBy() != null) {
            processApprove.setWorkerBy(
                    workerRepository.findById(processApproveDTO.getWorkerBy())
                            .orElseThrow(() -> new IllegalArgumentException("No such workerBy")));
        }
        if (processApproveDTO.getWorkerTo() != null) {
            processApprove.setWorkerTo(
                    workerRepository.findById(processApproveDTO.getWorkerTo())
                            .orElseThrow(() -> new IllegalArgumentException("No such workerTo")));
        }

        return processApprove;
    }

}
