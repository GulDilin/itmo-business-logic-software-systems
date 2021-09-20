package guldilin.service.implementation;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.model.MedicamentInterract;
import guldilin.model.Worker;
import guldilin.repository.MedicamentInterractRepository;
import guldilin.repository.MedicamentRepository;
import guldilin.service.interfaces.MedicamentInterractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public MedicamentInterractDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(medicamentInterractRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(MedicamentInterract.class.getName())));
    }

    @Override
    public MedicamentInterractDTO create(MedicamentInterractDTO medicamentInterractDTO) throws NoSuchObject {
        medicamentInterractDTO.setId(null);
        return mapToDTO(medicamentInterractRepository.save(mapToEntity(medicamentInterractDTO)));
    }

    private MedicamentInterractDTO mapToDTO(MedicamentInterract medicamentInterract) {
        return new MedicamentInterractDTO(medicamentInterract);
    }

    private MedicamentInterract mapToEntity(MedicamentInterractDTO medicamentInterractDTO) throws NoSuchObject {
        MedicamentInterract medicamentInterract = modelMapper.map(medicamentInterractDTO, MedicamentInterract.class);
        if (medicamentInterractDTO.getMedicament1() != null) {
            medicamentInterract.setMedicament1(
                    medicamentRepository.findById(medicamentInterractDTO.getMedicament1())
                            .orElseThrow(() -> new NoSuchObject(Worker.class.getName())));
        }
        if (medicamentInterractDTO.getMedicament2() != null) {
            medicamentInterract.setMedicament2(
                    medicamentRepository.findById(medicamentInterractDTO.getMedicament2())
                            .orElseThrow(() -> new NoSuchObject(Worker.class.getName())));
        }

        return medicamentInterract;
    }
}
