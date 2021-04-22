package guldilin.service;

import guldilin.dto.MedicamentDTO;
import guldilin.dto.UpdateMedicamentDTO;
import guldilin.model.Medicament;
import guldilin.repository.MedicamentTypeRepository;
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
    private final MedicamentTypeRepository medicamentTypeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentServiceImpl(MedicamentRepository medicamentRepository, MedicamentFormulaRepository medicamentFormulaRepository, MedicamentGroupRepository medicamentGroupRepository, MedicamentTypeRepository medicamentTypeRepository, ModelMapper modelMapper) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentFormulaRepository = medicamentFormulaRepository;
        this.medicamentGroupRepository = medicamentGroupRepository;
        this.medicamentTypeRepository = medicamentTypeRepository;
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
        throw new IllegalArgumentException("No such medicament");
    }

    @Override
    public MedicamentDTO create(MedicamentDTO medicamentDTO) {
        medicamentDTO.setId(null);
        if (medicamentRepository.findAllByTitle(medicamentDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Medicament with title already exists");
        }
        return mapToDTO(medicamentRepository.save(mapToEntity(medicamentDTO)));
    }

    @Override
    public MedicamentDTO update(UpdateMedicamentDTO updateMedicamentDTO) {
        Medicament medicament = medicamentRepository.findById(updateMedicamentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("No such medicament"));

        Medicament newMedicament = mapToEntity(updateMedicamentDTO);
        if (updateMedicamentDTO.getTitle() != null) {
            medicament.setTitle(updateMedicamentDTO.getTitle());
        }
        if (newMedicament.getMedicamentType() != null) {
            medicament.setMedicamentType(newMedicament.getMedicamentType());
        }
        if (newMedicament.getFormula() != null) {
            medicament.setFormula(newMedicament.getFormula());
        }
        if (newMedicament.getGroup() != null) {
            medicament.setGroup(newMedicament.getGroup());
        }
        return  mapToDTO(medicamentRepository.save(medicament));
    }

    private MedicamentDTO mapToDTO(Medicament medicament) {
        return new MedicamentDTO(medicament);
    }

    private Medicament mapToEntity(MedicamentDTO medicamentDTO) {
        Medicament medicament = modelMapper.map(medicamentDTO, Medicament.class);
        if (medicamentDTO.getFormula() != null) {
            medicament.setFormula(
                    medicamentFormulaRepository.findById(medicamentDTO.getFormula())
                            .orElseThrow(() -> new IllegalArgumentException("No such formula")));
        }
        if (medicamentDTO.getGroup() != null) {
            medicament.setGroup(
                    medicamentGroupRepository.findById(medicamentDTO.getGroup())
                            .orElseThrow(() -> new IllegalArgumentException("No such medical group")));
        }
        if (medicamentDTO.getMedicamentClass() != null) {
            medicament.setMedicamentType(
                    medicamentTypeRepository.findById(medicamentDTO.getMedicamentClass())
                            .orElseThrow(() -> new IllegalArgumentException("No such medical class")));
        }
        return medicament;
    }
}
