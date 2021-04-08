package guldilin.service;

import guldilin.dto.ProcessDTO;
import guldilin.model.Process;
import guldilin.repository.MedicamentRepository;
import guldilin.repository.ProcessRepository;
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
        List<Process> processList;

        if (status != null) {
            processList = processRepository.findAllByStatus(status);
        } else if (medicamentId != null) {
            processList = processRepository.findAllByMedicamentId(medicamentId);
        } else {
            processList = processRepository.findAll();
        }
        return processList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProcessDTO get(Integer id) {
        Optional<Process> found = processRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public ProcessDTO create(ProcessDTO ProcessDTO) {
        if (processRepository.findById(ProcessDTO.getId()).isPresent()) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(processRepository.save(mapToEntity(ProcessDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private ProcessDTO mapToDTO(Process process) {
        return new ProcessDTO((process));
    }

    private Process mapToEntity(ProcessDTO processDTO) {
        Process process = modelMapper.map(processDTO, Process.class);
        process.setMedicament(
                medicamentRepository.findById(processDTO.getMedicament())
                        .orElseThrow(() -> new IllegalArgumentException("No such medicament")));
        return process;
    }

}
