package guldilin.service.implementation;

import guldilin.dto.MedicamentGroupDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;
import guldilin.model.MedicamentGroup;
import guldilin.repository.MedicamentGroupRepository;
import guldilin.service.interfaces.MedicamentGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentGroupServiceImpl implements MedicamentGroupService {
    private final MedicamentGroupRepository medicamentGroupRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentGroupServiceImpl(MedicamentGroupRepository medicamentGroupRepository, ModelMapper modelMapper) {
        this.medicamentGroupRepository = medicamentGroupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentGroupDTO> getAll(String title, String description) {
        return medicamentGroupRepository.findAll()
                .stream()
                .filter(e -> title == null || e.getTitle().equals(title))
                .filter(e -> description == null || e.getDescription().equals(description))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentGroupDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(medicamentGroupRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(MedicamentGroup.class.getName())));
    }

    @Override
    public MedicamentGroupDTO create(MedicamentGroupDTO medicamentGroupDTO) throws ObjectAlreadyExists {
        medicamentGroupDTO.setId(null);
        if (medicamentGroupRepository.findAllByTitle(medicamentGroupDTO.getTitle()).size() > 0) {
            throw new ObjectAlreadyExists(MedicamentGroup.class.getName());
        }
        return mapToDTO(medicamentGroupRepository.save(mapToEntity(medicamentGroupDTO)));
    }

    private MedicamentGroupDTO mapToDTO(MedicamentGroup medicamentGroup) {
        return modelMapper.map(medicamentGroup, MedicamentGroupDTO.class);
    }

    private MedicamentGroup mapToEntity(MedicamentGroupDTO medicamentGroupDTO) {
        return modelMapper.map(medicamentGroupDTO, MedicamentGroup.class);
    }
}
