package guldilin.service.implementation;

import guldilin.dto.ProcessApproveDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.ProcessApprove;
import guldilin.model.Worker;
import guldilin.repository.ProcessApproveRepository;
import guldilin.repository.ProcessRepository;
import guldilin.repository.WorkerRepository;
import guldilin.service.interfaces.ProcessApproveService;
import org.hibernate.jdbc.Work;
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
    public List<ProcessApproveDTO> getAll(
            String level, Long processApproveById, Long processApproveToId, Date create, Date update) {
        return processApproveRepository
                .findAll()
                .stream()
                .filter(p -> level == null || p.getLevel().equals(Integer.parseInt(level)))
                .filter(p -> processApproveById == null || p.getWorkerBy().equals(processApproveById))
                .filter(p -> processApproveToId == null || p.getWorkerTo().equals(processApproveToId))
                .filter(p -> create == null || p.getCreated().equals(create))
                .filter(p -> update == null || p.getUpdated().equals(update))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProcessApproveDTO get(Integer id) throws NoSuchObject {
        Optional<ProcessApprove> found = processApproveRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new NoSuchObject(Process.class.getName());
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
    public ProcessApproveDTO update(ProcessApproveDTO processApproveDTO) throws NoSuchObject {
        processApproveRepository.findById(processApproveDTO.getId())
                .orElseThrow( () -> new NoSuchObject(Process.class.getName()) );
        processApproveDTO.setUpdated(new Date());
        return mapToDTO(processApproveRepository.save(mapToEntity(processApproveDTO)));
    }

    private ProcessApproveDTO mapToDTO(ProcessApprove processApprove) {
        return new ProcessApproveDTO(processApprove);
    }

    private ProcessApprove mapToEntity(ProcessApproveDTO processApproveDTO) throws NoSuchObject {

        ProcessApprove processApprove = modelMapper.map(processApproveDTO, ProcessApprove.class);
        if (processApproveDTO.getProcess() != null) {
            processApprove.setProcess(
                    processRepository.findById(processApproveDTO.getProcess())
                            .orElseThrow(() -> new NoSuchObject(Process.class.getName())));
        }
        if (processApproveDTO.getWorkerBy() != null) {
            processApprove.setWorkerBy(
                    workerRepository.findById(processApproveDTO.getWorkerBy())
                            .orElseThrow(() -> new NoSuchObject(Worker.class.getName())).getId());
        }
        if (processApproveDTO.getWorkerTo() != null) {
            processApprove.setWorkerTo(
                    workerRepository.findById(processApproveDTO.getWorkerTo())
                            .orElseThrow(() -> new NoSuchObject(Worker.class.getName())).getId());
        }

        return processApprove;
    }

}
