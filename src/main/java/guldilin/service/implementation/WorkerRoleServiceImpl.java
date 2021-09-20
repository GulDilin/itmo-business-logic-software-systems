package guldilin.service.implementation;

import guldilin.dto.WorkerRoleDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;
import guldilin.model.WorkerRole;
import guldilin.repository.WorkerRoleRepository;
import guldilin.service.interfaces.WorkerRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerRoleServiceImpl implements WorkerRoleService {
    private final WorkerRoleRepository workerRoleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WorkerRoleServiceImpl(WorkerRoleRepository workerRoleRepository, ModelMapper modelMapper) {
        this.workerRoleRepository = workerRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkerRoleDTO> getAll(String title, Integer level) {
        return workerRoleRepository.findAll()
                .stream()
                .filter(w -> title == null || w.getTitle().equals(title))
                .filter(w -> level == null || w.getLevel().equals(level))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkerRoleDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(workerRoleRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(WorkerRole.class.getName())));
    }

    @Override
    public WorkerRoleDTO create(WorkerRoleDTO workerRoleDTO) throws ObjectAlreadyExists {
        if (workerRoleRepository.findAllByTitle(workerRoleDTO.getTitle()).size() > 0) {
            throw new ObjectAlreadyExists(WorkerRole.class.getName());
        }
        return mapToDTO(workerRoleRepository.save(mapToEntity(workerRoleDTO)));
    }

    private WorkerRoleDTO mapToDTO(WorkerRole workerRole) {
        return modelMapper.map(workerRole, WorkerRoleDTO.class);
    }

    private WorkerRole mapToEntity(WorkerRoleDTO workerRoleDTO) {
        return modelMapper.map(workerRoleDTO, WorkerRole.class);
    }
}
