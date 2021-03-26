package guldilin.service;

import guldilin.dto.WorkerRoleDTO;
import guldilin.model.WorkerRole;
import guldilin.repository.WorkerRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkerRoleServiceImpl implements WorkerRoleService {
    private final WorkerRoleRepository workerRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public WorkerRoleServiceImpl(WorkerRoleRepository workerRoleRepository) {
        this.workerRoleRepository = workerRoleRepository;
    }

    @Override
    public List<WorkerRoleDTO> getAll(String title, Integer level) {
        List<WorkerRole> workerRoleList;

        if (title != null) {
            workerRoleList = workerRoleRepository.findAllByTitle(title);
        } else if (level != null) {
            workerRoleList = workerRoleRepository.findAllByLevel(level);
        } else {
            workerRoleList = workerRoleRepository.findAll();
        }
        return workerRoleList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkerRoleDTO get(Integer id) {
        Optional<WorkerRole> found = workerRoleRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public WorkerRoleDTO create(WorkerRoleDTO workerRoleDTO) {
        return mapToDTO(workerRoleRepository.save(mapToEntity(workerRoleDTO)));
    }

    private WorkerRoleDTO mapToDTO(WorkerRole workerRole) {
        return  modelMapper.map(workerRole, WorkerRoleDTO.class);
    }

    private WorkerRole mapToEntity(WorkerRoleDTO workerRoleDTO) {
        return  modelMapper.map(workerRoleDTO, WorkerRole.class);
    }
}
