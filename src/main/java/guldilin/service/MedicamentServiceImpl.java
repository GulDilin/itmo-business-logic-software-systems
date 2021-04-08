package guldilin.service;

import guldilin.dto.MedicamentDTO;
import guldilin.model.Medicament;
import guldilin.repository.MedicamentClassRepository;
import guldilin.repository.MedicamentFormulaRepository;
import guldilin.repository.MedicamentGroupRepository;
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
    private final MedicamentFormulaRepository medicamentFormulaRepository;
    private final MedicamentGroupRepository medicamentGroupRepository;
    private final MedicamentClassRepository medicamentClassRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentServiceImpl(MedicamentRepository medicamentRepository, MedicamentFormulaRepository medicamentFormulaRepository, MedicamentGroupRepository medicamentGroupRepository, MedicamentClassRepository medicamentClassRepository, ModelMapper modelMapper) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentFormulaRepository = medicamentFormulaRepository;
        this.medicamentGroupRepository = medicamentGroupRepository;
        this.medicamentClassRepository = medicamentClassRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentDTO> getAll(String title, Long groupId, Long formulaId, Long activeSubstanceId) {
        List<Medicament> MedicamentList;

        if (title != null) {
            MedicamentList = medicamentRepository.findAllByTitle(title);
        } else if (groupId != null) {
            MedicamentList = medicamentRepository.findAllByGroupId(groupId);
        } else if (formulaId != null) {
            MedicamentList = medicamentRepository.findAllByFormulaId(formulaId);
        } else if (activeSubstanceId != null) {
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

    private MedicamentDTO mapToDTO(Medicament medicament) {
        return new MedicamentDTO(medicament);
    }

    private Medicament mapToEntity(MedicamentDTO medicamentDTO) {
        Medicament medicament = modelMapper.map(medicamentDTO, Medicament.class);
        medicament.setFormula(
                medicamentFormulaRepository.findById(medicamentDTO.getFormula())
                        .orElseThrow(() -> new IllegalArgumentException("No such formula")));
        medicament.setGroup(
                medicamentGroupRepository.findById(medicamentDTO.getGroup())
                        .orElseThrow(() -> new IllegalArgumentException("No such medical group")));
        medicament.setMedicamentClass(
                medicamentClassRepository.findById(medicamentDTO.getMedicamentClass())
                        .orElseThrow(() -> new IllegalArgumentException("No such medical class")));
        return medicament;
    }
}
