package guldilin.service.implementation;

import guldilin.dto.MedicamentTypeDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;
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
    public MedicamentTypeDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(medicamentTypeRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(MedicamentType.class.getName())));
    }

    @Override
    public MedicamentTypeDTO create(MedicamentTypeDTO medicamentTypeDTO) throws ObjectAlreadyExists, NoSuchObject {
        medicamentTypeDTO.setId(null);
        if (medicamentTypeRepository.findAllByTitle(medicamentTypeDTO.getTitle()).size() > 0) {
            throw new ObjectAlreadyExists(MedicamentType.class.getName());
        }
        return mapToDTO(medicamentTypeRepository.save(mapToEntity(medicamentTypeDTO)));
    }

    private MedicamentTypeDTO mapToDTO(MedicamentType medicamentType) {
        return new MedicamentTypeDTO(medicamentType);
    }

    private MedicamentType mapToEntity(MedicamentTypeDTO medicamentTypeDTO) throws NoSuchObject {
        MedicamentType medicamentType = modelMapper.map(medicamentTypeDTO, MedicamentType.class);
        if (medicamentTypeDTO.getParentClass() != null) {
            medicamentType.setParentClass(
                    medicamentTypeRepository.findById(medicamentTypeDTO.getParentClass())
                            .orElseThrow(() -> new NoSuchObject(MedicamentType.class.getName())));
        }
        return medicamentType;
    }
}
