package guldilin.service;

import guldilin.dto.WorkerRoleDTO;
import guldilin.model.WorkerRole;
import guldilin.repository.WorkerRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
        if (workerRoleRepository.findAllByTitle(workerRoleDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(workerRoleRepository.save(mapToEntity(workerRoleDTO)));
        } catch (IllegalArgumentException exp) {
            throw exp;
        } catch (InvalidDataAccessApiUsageException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot save to database");
        }
    }

    private WorkerRoleDTO mapToDTO(WorkerRole workerRole) {
        return  modelMapper.map(workerRole, WorkerRoleDTO.class);
    }

    private WorkerRole mapToEntity(WorkerRoleDTO workerRoleDTO) {
        return  modelMapper.map(workerRoleDTO, WorkerRole.class);
    }
}
