package guldilin.service;

import guldilin.dto.MedicamentDTO;
import guldilin.model.Medicament;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicamentServiceImpl implements MedicamentService {
    private final guldilin.repository.MedicamentRepository MedicamentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentServiceImpl(guldilin.repository.MedicamentRepository MedicamentRepository) {
        this.MedicamentRepository = MedicamentRepository;
    }

    @Override
    public List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId) {
        List<Medicament> MedicamentList;

        if (title != null) {
            MedicamentList = MedicamentRepository.findAllByTitle(title);
        }  else if (groupId != null) {
            MedicamentList = MedicamentRepository.findAllByGroupId(groupId);
        }  else if (formulaId != null) {
            MedicamentList = MedicamentRepository.findAllByFormulaId(formulaId);
        }  else if (activeSubstanceId != null) {
            MedicamentList = MedicamentRepository.findAllByActiveSubstancesId(activeSubstanceId);
        } else {
            MedicamentList = MedicamentRepository.findAll();
        }
        return MedicamentList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentDTO get(Integer id) {
        Optional<Medicament> found = MedicamentRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentDTO create(MedicamentDTO MedicamentDTO) {
        if (MedicamentRepository.findAllByTitle(MedicamentDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(MedicamentRepository.save(mapToEntity(MedicamentDTO)));
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
