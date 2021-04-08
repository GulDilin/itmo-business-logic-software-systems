package guldilin.service;

import guldilin.dto.MedicamentDTO;
import guldilin.model.Medicament;
import guldilin.repository.MedicamentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicamentServiceImpl implements MedicamentService {
    private final MedicamentRepository medicamentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentServiceImpl(MedicamentRepository medicamentRepository, ModelMapper modelMapper) {
        this.medicamentRepository = medicamentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId) {
        List<Medicament> MedicamentList;

        if (title != null) {
            MedicamentList = medicamentRepository.findAllByTitle(title);
        }  else if (groupId != null) {
            MedicamentList = medicamentRepository.findAllByGroupId(groupId);
        }  else if (formulaId != null) {
            MedicamentList = medicamentRepository.findAllByFormulaId(formulaId);
        }  else if (activeSubstanceId != null) {
            MedicamentList = medicamentRepository.findAllByActiveSubstancesId(activeSubstanceId);
        } else {
            MedicamentList = medicamentRepository.findAll();
        }
        return MedicamentList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentDTO get(Integer id) {
        Optional<Medicament> found = medicamentRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentDTO create(MedicamentDTO MedicamentDTO) {
        if (medicamentRepository.findAllByTitle(MedicamentDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(medicamentRepository.save(mapToEntity(MedicamentDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentDTO mapToDTO(Medicament Medicament) {
        return  modelMapper.map(Medicament, MedicamentDTO.class);
    }

    private Medicament mapToEntity(MedicamentDTO MedicamentDTO) {
        return  modelMapper.map(MedicamentDTO, Medicament.class);
    }
}
