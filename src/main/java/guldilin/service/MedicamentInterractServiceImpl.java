package guldilin.service;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.dto.WorkerDTO;
import guldilin.model.MedicamentInterract;
import guldilin.model.Worker;
import guldilin.repository.MedicamentInterractRepository;
import guldilin.repository.MedicamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicamentInterractServiceImpl implements MedicamentInterractService {
    private final MedicamentInterractRepository medicamentInterractRepository;
    private final MedicamentRepository medicamentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentInterractServiceImpl(MedicamentInterractRepository medicamentInterractRepository, MedicamentRepository medicamentRepository) {
        this.medicamentInterractRepository = medicamentInterractRepository;
        this.medicamentRepository = medicamentRepository;
    }

    @Override
    public List<MedicamentInterractDTO> getAll(String description) {
        List<MedicamentInterract> MedicamentInterractList;

       if (description != null) {
            MedicamentInterractList = medicamentInterractRepository.findAllByDescription(description);
        } else {
            MedicamentInterractList = medicamentInterractRepository.findAll();
        }
        return MedicamentInterractList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentInterractDTO get(Integer id) {
        Optional<MedicamentInterract> found = medicamentInterractRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentInterractDTO create(MedicamentInterractDTO MedicamentInterractDTO) {
        if (medicamentInterractRepository.findById(MedicamentInterractDTO.getId()).isPresent()) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(medicamentInterractRepository.save(mapToEntity(MedicamentInterractDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentInterractDTO mapToDTO(MedicamentInterract medicamentInterract) {
        return new MedicamentInterractDTO(medicamentInterract);
    }

    private MedicamentInterract mapToEntity(MedicamentInterractDTO medicamentInterractDTO) {
        MedicamentInterract medicamentInterract = modelMapper.map(medicamentInterractDTO, MedicamentInterract.class);
        medicamentInterract.setMedicament1(
                medicamentRepository.findById(medicamentInterractDTO.getMedicament1())
                    .orElseThrow(() -> new IllegalArgumentException("No such worker medicament 1")));
        medicamentInterract.setMedicament2(
                medicamentRepository.findById(medicamentInterractDTO.getMedicament2())
                        .orElseThrow(() -> new IllegalArgumentException("No such worker medicament 2")));

        return medicamentInterract;
    }
}
