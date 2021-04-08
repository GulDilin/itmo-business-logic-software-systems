package guldilin.service;

import guldilin.dto.MedicamentGroupDTO;
import guldilin.model.MedicamentGroup;
import guldilin.repository.MedicamentGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        List<MedicamentGroup> MedicamentGroupList;

        if (title != null) {
            MedicamentGroupList = medicamentGroupRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentGroupList = medicamentGroupRepository.findAllByDescription(description);
        } else {
            MedicamentGroupList = medicamentGroupRepository.findAll();
        }
        return MedicamentGroupList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentGroupDTO get(Integer id) {
        Optional<MedicamentGroup> found = medicamentGroupRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such group");
    }

    @Override
    public MedicamentGroupDTO create(MedicamentGroupDTO medicamentGroupDTO) {
        medicamentGroupDTO.setId(null);
        if (medicamentGroupRepository.findAllByTitle(medicamentGroupDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Group with title already exists");
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
