package guldilin.service;

import guldilin.dto.MedicamentInterractDTO;
import guldilin.model.MedicamentInterract;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedicamentInterractServiceImpl implements MedicamentInterractService {
    private final guldilin.repository.MedicamentInterractRepository MedicamentInterractRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicamentInterractServiceImpl(guldilin.repository.MedicamentInterractRepository MedicamentInterractRepository) {
        this.MedicamentInterractRepository = MedicamentInterractRepository;
    }

    @Override
    public List<MedicamentInterractDTO> getAll(String description) {
        List<MedicamentInterract> MedicamentInterractList;

       if (description != null) {
            MedicamentInterractList = MedicamentInterractRepository.findAllByDescription(description);
        } else {
            MedicamentInterractList = MedicamentInterractRepository.findAll();
        }
        return MedicamentInterractList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentInterractDTO get(Integer id) {
        Optional<MedicamentInterract> found = MedicamentInterractRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such role");
    }

    @Override
    public MedicamentInterractDTO create(MedicamentInterractDTO MedicamentInterractDTO) {
        if (MedicamentInterractRepository.findAllById(MedicamentInterractDTO.getId()).size() > 0) {
            throw new IllegalArgumentException("Role with title already exists");
        }
        try {
            return mapToDTO(MedicamentInterractRepository.save(mapToEntity(MedicamentInterractDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private MedicamentInterractDTO mapToDTO(MedicamentInterract MedicamentInterract) {
        return  modelMapper.map(MedicamentInterract, MedicamentInterractDTO.class);
    }

    private MedicamentInterract mapToEntity(MedicamentInterractDTO MedicamentInterractDTO) {
        return  modelMapper.map(MedicamentInterractDTO, MedicamentInterract.class);
    }
}
