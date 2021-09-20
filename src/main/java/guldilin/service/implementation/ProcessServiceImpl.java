package guldilin.service.implementation;

import guldilin.dto.ProcessDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.Process;
import guldilin.repository.MedicamentRepository;
import guldilin.repository.ProcessRepository;
import guldilin.service.interfaces.ProcessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final ProcessRepository processRepository;
    private final MedicamentRepository medicamentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProcessServiceImpl(ProcessRepository processRepository, MedicamentRepository medicamentRepository, ModelMapper modelMapper) {
        this.processRepository = processRepository;
        this.medicamentRepository = medicamentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProcessDTO> getAll(String status, Long medicamentId) {
        return processRepository.findAll()
                .stream()
                .filter(e -> status == null || e.getStatus().equals(status))
                .filter(e -> e.getMedicament().getId().equals(medicamentId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProcessDTO get(Integer id) throws NoSuchObject {
        return  mapToDTO(processRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new NoSuchObject(Process.class.getName())
        ));
    }

    @Override
    public ProcessDTO create(ProcessDTO processDTO) throws NoSuchObject {
        processDTO.setId(null);
        return mapToDTO(processRepository.save(mapToEntity(processDTO)));
    }

    private ProcessDTO mapToDTO(Process process) {
        return new ProcessDTO((process));
    }

    private Process mapToEntity(ProcessDTO processDTO) throws NoSuchObject {
        Process process = modelMapper.map(processDTO, Process.class);
        if (processDTO.getMedicament() != null) {
            process.setMedicament(
                    medicamentRepository.findById(processDTO.getMedicament())
                            .orElseThrow(() -> new NoSuchObject(Process.class.getName())));
        }
        return process;
    }

}
