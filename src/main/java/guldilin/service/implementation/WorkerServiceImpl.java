package guldilin.service.implementation;

import guldilin.dto.WorkerDTO;
import guldilin.dto.WorkerRegistrationRequestDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.Worker;
import guldilin.repository.WorkerRepository;
import guldilin.repository.WorkerRoleRepository;
import guldilin.service.interfaces.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final WorkerRoleRepository workerRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WorkerServiceImpl(
            WorkerRepository workerRepository,
            WorkerRoleRepository workerRoleRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.workerRepository = workerRepository;
        this.workerRoleRepository = workerRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<WorkerDTO> getAll(String name, Long workerRoleId, String email, String login, String password) {
        return workerRepository.findAll()
                .stream()
                .filter(w -> name == null || w.getName().equals(name))
                .filter(w -> workerRoleId == null || w.getRole().getId().equals(workerRoleId))
                .filter(w -> email == null || w.getEmail().equals(email))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public WorkerDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(workerRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(Worker.class.getName())));
    }

    @Override
    public WorkerDTO create(WorkerRegistrationRequestDTO workerDTO) {
        Optional<Worker> found = workerRepository.findByLogin(workerDTO.getLogin());
        if (found.isPresent()) {
            throw new IllegalArgumentException("Worker with such login already exists");
        }
        Worker worker = mapToEntity(workerDTO);
        worker.setPassword(passwordEncoder.encode(workerDTO.getPassword()));
        return mapToDTO(workerRepository.save(worker));
    }

    public WorkerDTO findByLoginAndPassword(String login, String password) {
        Worker worker = workerRepository.findByLogin(login).orElseThrow(
                () -> new IllegalArgumentException("User or password is incorrect")
        );
        if (!passwordEncoder.matches(password, worker.getPassword())) {
            throw new IllegalArgumentException("User or password is incorrect");
        }
        return mapToDTO(worker);
    }

    public WorkerDTO mapToDTO(Worker worker) {
        return new WorkerDTO(worker);
    }

    private Worker mapToEntity(WorkerDTO workerDTO) {
        Worker worker = modelMapper.map(workerDTO, Worker.class);
        if (workerDTO.getRole() != null) {
            worker.setRole(
                    workerRoleRepository.findByTitle(workerDTO.getRole())
                            .orElseThrow(() -> new IllegalArgumentException("No such worker role")));
        }
        return worker;
    }

}
