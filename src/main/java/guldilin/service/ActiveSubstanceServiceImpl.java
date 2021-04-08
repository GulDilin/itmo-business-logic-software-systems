package guldilin.service;

import guldilin.dto.ActiveSubstanceDTO;
import guldilin.model.ActiveSubstance;
import guldilin.repository.ActiveSubstanceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiveSubstanceServiceImpl implements ActiveSubstanceService {
    private final ActiveSubstanceRepository activeSubstanceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ActiveSubstanceServiceImpl(ActiveSubstanceRepository activeSubstanceRepository, ModelMapper modelMapper) {
        this.activeSubstanceRepository = activeSubstanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActiveSubstanceDTO> getAll(String title, String description) {
        List<ActiveSubstance> ActiveSubstanceList;

        if (title != null) {
            ActiveSubstanceList = activeSubstanceRepository.findAllByTitle(title);
        } else if (description != null) {
            ActiveSubstanceList = activeSubstanceRepository.findAllByDescription(description);
        } else{
            ActiveSubstanceList = activeSubstanceRepository.findAll();
        }
        return ActiveSubstanceList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override public ActiveSubstanceDTO get(Integer id) {
        Optional<ActiveSubstance> found = activeSubstanceRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such ");
    }

    @Override
    public ActiveSubstanceDTO create(ActiveSubstanceDTO ActiveSubstanceDTO) {
        if (activeSubstanceRepository.findAllByTitle(ActiveSubstanceDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException(" with name already exists");
        }
        try {
            return mapToDTO(activeSubstanceRepository.save(mapToEntity(ActiveSubstanceDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private ActiveSubstanceDTO mapToDTO(ActiveSubstance activeSubstance) {
        return  modelMapper.map(activeSubstance, ActiveSubstanceDTO.class);
    }

    private ActiveSubstance mapToEntity(ActiveSubstanceDTO activeSubstanceDTO) {
        return  modelMapper.map(activeSubstanceDTO, ActiveSubstance.class);
    }
}
