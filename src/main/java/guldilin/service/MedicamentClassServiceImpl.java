package guldilin.service;

import guldilin.dto.MedicamentClassDTO;
import guldilin.model.MedicamentClass;
import guldilin.repository.MedicamentClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentClassServiceImpl implements MedicamentClassService {
    private final MedicamentClassRepository MedicamentClassRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentClassServiceImpl(MedicamentClassRepository MedicamentClassRepository) {
        this.MedicamentClassRepository = MedicamentClassRepository;
    }

    @Override
    public List<MedicamentClassDTO> getAll(String title, String description) {
        List<MedicamentClass> MedicamentClassList;

        if (title != null) {
            MedicamentClassList = MedicamentClassRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentClassList = MedicamentClassRepository.findAllByDescription(description);
        } else {
            MedicamentClassList = MedicamentClassRepository.findAll();
        }
        return MedicamentClassList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentClassDTO get(Integer id) {
        Optional<MedicamentClass> found = MedicamentClassRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentClassDTO create(MedicamentClassDTO MedicamentClassDTO) {
        if (MedicamentClassRepository.findAllByTitle(MedicamentClassDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(MedicamentClassRepository.save(mapToEntity(MedicamentClassDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentClassDTO mapToDTO(MedicamentClass MedicamentClass) {
        return  modelMapper.map(MedicamentClass, MedicamentClassDTO.class);
    }

    private MedicamentClass mapToEntity(MedicamentClassDTO MedicamentClassDTO) {
        return  modelMapper.map(MedicamentClassDTO, MedicamentClass.class);
    }
}
