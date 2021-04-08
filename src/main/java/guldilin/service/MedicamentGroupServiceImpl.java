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
    private final guldilin.repository.MedicamentGroupRepository MedicamentGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentGroupServiceImpl(guldilin.repository.MedicamentGroupRepository MedicamentGroupRepository) {
        this.MedicamentGroupRepository = MedicamentGroupRepository;
    }

    @Override
    public List<MedicamentGroupDTO> getAll(String title, String description) {
        List<MedicamentGroup> MedicamentGroupList;

        if (title != null) {
            MedicamentGroupList = MedicamentGroupRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentGroupList = MedicamentGroupRepository.findAllByDescription(description);
        } else {
            MedicamentGroupList = MedicamentGroupRepository.findAll();
        }
        return MedicamentGroupList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentGroupDTO get(Integer id) {
        Optional<MedicamentGroup> found = MedicamentGroupRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentGroupDTO create(MedicamentGroupDTO MedicamentGroupDTO) {
        if (MedicamentGroupRepository.findAllByTitle(MedicamentGroupDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(MedicamentGroupRepository.save(mapToEntity(MedicamentGroupDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentGroupDTO mapToDTO(MedicamentGroup MedicamentGroup) {
        return  modelMapper.map(MedicamentGroup, MedicamentGroupDTO.class);
    }

    private MedicamentGroup mapToEntity(MedicamentGroupDTO MedicamentGroupDTO) {
        return  modelMapper.map(MedicamentGroupDTO, MedicamentGroup.class);
    }
}
