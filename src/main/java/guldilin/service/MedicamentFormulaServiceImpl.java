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
    private final guldilin.repository.MedicamentFormulaRepository MedicamentFormulaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentFormulaServiceImpl(MedicamentFormulaRepository MedicamentFormulaRepository) {
        this.MedicamentFormulaRepository = MedicamentFormulaRepository;
    }

    @Override
    public List<MedicamentFormulaDTO> getAll(String title, String description) {
        List<MedicamentFormula> MedicamentFormulaList;

        if (title != null) {
            MedicamentFormulaList = MedicamentFormulaRepository.findAllByTitle(title);
        } else if (description != null) {
            MedicamentFormulaList = MedicamentFormulaRepository.findAllByDescription(description);
        } else {
            MedicamentFormulaList = MedicamentFormulaRepository.findAll();
        }
        return MedicamentFormulaList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentFormulaDTO get(Integer id) {
        Optional<MedicamentFormula> found = MedicamentFormulaRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentFormulaDTO create(MedicamentFormulaDTO MedicamentFormulaDTO) {
        if (MedicamentFormulaRepository.findAllByTitle(MedicamentFormulaDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(MedicamentFormulaRepository.save(mapToEntity(MedicamentFormulaDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentFormulaDTO mapToDTO(MedicamentFormula MedicamentFormula) {
        return  modelMapper.map(MedicamentFormula, MedicamentFormulaDTO.class);
    }

    private MedicamentFormula mapToEntity(MedicamentFormulaDTO MedicamentFormulaDTO) {
        return  modelMapper.map(MedicamentFormulaDTO, MedicamentFormula.class);
    }
}
