package guldilin.service;

import guldilin.dto.WorkerDTO;
import guldilin.model.Worker;
import guldilin.repository.WorkerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, ModelMapper modelMapper) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkerDTO> getAll(String name, Long workerRoleId, String email) {
        List<Worker> workerList;

        if (name != null) {
            workerList = workerRepository.findAllByName(name);
        } else if (workerRoleId != null) {
            workerList = workerRepository.findAllByRoleId(workerRoleId);
        } else if (email != null) {
            workerList = workerRepository.findAllByEmail(email);
        } else{
            workerList = workerRepository.findAll();
        }
        return workerList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override public WorkerDTO get(Integer id) {
        Optional<Worker> found = workerRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such ");
    }

    @Override
    public WorkerDTO create(WorkerDTO workerDTO) {
        if (workerRepository.findAllByName(workerDTO.getName()).size() > 0) {
            throw new IllegalArgumentException(" with name already exists");
        }
        try {
            return mapToDTO(workerRepository.save(mapToEntity(workerDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private WorkerDTO mapToDTO(Worker worker) {
        return  modelMapper.map(worker, WorkerDTO.class);
    }

    private Worker mapToEntity(WorkerDTO workerDTO) {
        return  modelMapper.map(workerDTO, Worker.class);
    }
}
