package guldilin.service.implementation;

import guldilin.dto.MedicamentTypeDTO;
import guldilin.model.MedicamentType;
import guldilin.repository.MedicamentTypeRepository;
import guldilin.service.interfaces.MedicamentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentTypeServiceImpl implements MedicamentTypeService {
    private final MedicamentTypeRepository medicamentTypeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentTypeServiceImpl(MedicamentTypeRepository medicamentTypeRepository, ModelMapper modelMapper) {
        this.medicamentTypeRepository = medicamentTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentTypeDTO> getAll(String title, String description) {
        List<MedicamentType> medicamentTypeList;

        if (title != null) {
            medicamentTypeList = medicamentTypeRepository.findAllByTitle(title);
        } else if (description != null) {
            medicamentTypeList = medicamentTypeRepository.findAllByDescription(description);
        } else {
            medicamentTypeList = medicamentTypeRepository.findAll();
        }
        return medicamentTypeList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentTypeDTO get(Integer id) {
        Optional<MedicamentType> found = medicamentTypeRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such medicament class");
    }

    @Override
    public MedicamentTypeDTO create(MedicamentTypeDTO medicamentTypeDTO) {
        medicamentTypeDTO.setId(null);
        if (medicamentTypeRepository.findAllByTitle(medicamentTypeDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Medicament class with title already exists");
        }
        return mapToDTO(medicamentTypeRepository.save(mapToEntity(medicamentTypeDTO)));
    }

    private MedicamentTypeDTO mapToDTO(MedicamentType medicamentType) {
        return new MedicamentTypeDTO(medicamentType);
    }

    private MedicamentType mapToEntity(MedicamentTypeDTO medicamentTypeDTO) {
        MedicamentType medicamentType = modelMapper.map(medicamentTypeDTO, MedicamentType.class);
        if (medicamentTypeDTO.getParentClass() != null) {
            medicamentType.setParentClass(
                    medicamentTypeRepository.findById(medicamentTypeDTO.getParentClass())
                            .orElseThrow(() -> new IllegalArgumentException("No such worker parent class")));
        }


        return medicamentType;
    }
}
