package guldilin.service;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.model.MedicamentInterract;
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
        throw new IllegalArgumentException("No such medicament interaction");
    }

    @Override
    public MedicamentInterractDTO create(MedicamentInterractDTO medicamentInterractDTO) {
        medicamentInterractDTO.setId(null);
        return mapToDTO(medicamentInterractRepository.save(mapToEntity(medicamentInterractDTO)));
    }

    private MedicamentInterractDTO mapToDTO(MedicamentInterract medicamentInterract) {
        return new MedicamentInterractDTO(medicamentInterract);
    }

    private MedicamentInterract mapToEntity(MedicamentInterractDTO medicamentInterractDTO) {
        MedicamentInterract medicamentInterract = modelMapper.map(medicamentInterractDTO, MedicamentInterract.class);
        if (medicamentInterractDTO.getMedicament1() != null) {
            medicamentInterract.setMedicament1(
                    medicamentRepository.findById(medicamentInterractDTO.getMedicament1())
                            .orElseThrow(() -> new IllegalArgumentException("No such worker medicament 1")));
        }
        if (medicamentInterractDTO.getMedicament2() != null) {
            medicamentInterract.setMedicament2(
                    medicamentRepository.findById(medicamentInterractDTO.getMedicament2())
                            .orElseThrow(() -> new IllegalArgumentException("No such worker medicament 2")));
        }

        return medicamentInterract;
    }
}
