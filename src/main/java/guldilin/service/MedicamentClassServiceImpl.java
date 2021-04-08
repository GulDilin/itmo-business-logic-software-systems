package guldilin.service;

import guldilin.dto.MedicamentClassDTO;
import guldilin.model.MedicamentClass;
import guldilin.repository.MedicamentClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentClassServiceImpl implements MedicamentClassService {
    private final MedicamentClassRepository medicamentClassRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentClassServiceImpl(MedicamentClassRepository medicamentClassRepository, ModelMapper modelMapper) {
        this.medicamentClassRepository = medicamentClassRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentClassDTO> getAll(String title, String description) {
        List<MedicamentClass> MedicamentClassList;

        if (title != null) {
            MedicamentClassList = medicamentClassRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentClassList = medicamentClassRepository.findAllByDescription(description);
        } else {
            MedicamentClassList = medicamentClassRepository.findAll();
        }
        return MedicamentClassList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentClassDTO get(Integer id) {
        Optional<MedicamentClass> found = medicamentClassRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such medicament class");
    }

    @Override
    public MedicamentClassDTO create(MedicamentClassDTO medicamentClassDTO) {
        medicamentClassDTO.setId(null);
        if (medicamentClassRepository.findAllByTitle(medicamentClassDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Medicament class with title already exists");
        }
        try {
            return mapToDTO(medicamentClassRepository.save(mapToEntity(medicamentClassDTO)));
        } catch (IllegalArgumentException exp) {
            throw exp;
        } catch (InvalidDataAccessApiUsageException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot save to database");
        }
    }

    private MedicamentClassDTO mapToDTO(MedicamentClass medicamentClass) {
        return new MedicamentClassDTO(medicamentClass);
    }

    private MedicamentClass mapToEntity(MedicamentClassDTO medicamentClassDTO) {
        MedicamentClass medicamentClass = modelMapper.map(medicamentClassDTO, MedicamentClass.class);
        if (medicamentClassDTO.getParentClass() != null) {
            medicamentClass.setParentClass(
                    medicamentClassRepository.findById(medicamentClassDTO.getParentClass())
                            .orElseThrow(() -> new IllegalArgumentException("No such worker parent class")));
        }


        return medicamentClass;
    }
}
