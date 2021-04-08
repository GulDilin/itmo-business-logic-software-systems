package guldilin.service;

import guldilin.dto.MedicamentFormulaDTO;
import guldilin.model.MedicamentFormula;
import guldilin.repository.MedicamentFormulaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicamentFormulaServiceImpl implements MedicamentFormulaService {
    private final MedicamentFormulaRepository medicamentFormulaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MedicamentFormulaServiceImpl(MedicamentFormulaRepository medicamentFormulaRepository, ModelMapper modelMapper) {
        this.medicamentFormulaRepository = medicamentFormulaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MedicamentFormulaDTO> getAll(String title, String description) {
        List<MedicamentFormula> MedicamentFormulaList;

        if (title != null) {
            MedicamentFormulaList = medicamentFormulaRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentFormulaList = medicamentFormulaRepository.findAllByDescription(description);
        } else {
            MedicamentFormulaList = medicamentFormulaRepository.findAll();
        }
        return MedicamentFormulaList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentFormulaDTO get(Integer id) {
        Optional<MedicamentFormula> found = medicamentFormulaRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentFormulaDTO create(MedicamentFormulaDTO MedicamentFormulaDTO) {
        if (medicamentFormulaRepository.findAllByTitle(MedicamentFormulaDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(medicamentFormulaRepository.save(mapToEntity(MedicamentFormulaDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentFormulaDTO mapToDTO(MedicamentFormula medicamentFormula) {
        return  modelMapper.map(medicamentFormula, MedicamentFormulaDTO.class);
    }

    private MedicamentFormula mapToEntity(MedicamentFormulaDTO medicamentFormulaDTO) {
        return  modelMapper.map(medicamentFormulaDTO, MedicamentFormula.class);
    }
}
