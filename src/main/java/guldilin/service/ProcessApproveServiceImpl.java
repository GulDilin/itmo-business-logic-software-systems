package guldilin.service;

import com.jayway.jsonpath.internal.path.PredicatePathToken;
import guldilin.dto.ProcessApproveDTO;
import guldilin.model.ProcessApprove;
import guldilin.repository.ProcessApproveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessApproveServiceImpl implements ProcessApproveService {
    private final ProcessApproveRepository ProcessApproveRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProcessApproveServiceImpl(ProcessApproveRepository ProcessApproveRepository) {
        this.ProcessApproveRepository = ProcessApproveRepository;
    }

    @Override
    public List<ProcessApproveDTO> getAll(String level, Long ProcessApproveBy, Long ProcessApproveTo, Date create, Date update) {
        List<ProcessApprove> ProcessApproveList;

        if (level != null) {
            ProcessApproveList = ProcessApproveRepository.findAllByLevel(level);
        } else if (ProcessApproveBy != null) {
            ProcessApproveList = ProcessApproveRepository.findAllByIdWorkerBy(ProcessApproveBy);
        } else if (ProcessApproveTo != null) {
            ProcessApproveList = ProcessApproveRepository.findAllByIdWorkerBy(ProcessApproveTo);
        } else if (create != null) {
            ProcessApproveList = ProcessApproveRepository.findAllByCreated(create);
        } else if (update != null) {
            ProcessApproveList = ProcessApproveRepository.findAllByUpdated(update);
        } else{
            ProcessApproveList = ProcessApproveRepository.findAll();
        }
        return ProcessApproveList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override public ProcessApproveDTO get(Integer id) {
        Optional<ProcessApprove> found = ProcessApproveRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such ");
    }

    @Override
    public ProcessApproveDTO create(ProcessApproveDTO ProcessApproveDTO) {
        if (ProcessApproveRepository.findAllById(ProcessApproveDTO.getId()).size() > 0) {
            throw new IllegalArgumentException(" with name already exists");
        }
        try {
            return mapToDTO(ProcessApproveRepository.save(mapToEntity(ProcessApproveDTO)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't Save to Database");
        }
    }

    private ProcessApproveDTO mapToDTO(ProcessApprove processApprove) {
        return modelMapper.map(processApprove, ProcessApproveDTO.class);
    }

    private ProcessApprove mapToEntity(ProcessApproveDTO processApproveDTO) {
        return  modelMapper.map(processApproveDTO, ProcessApprove.class);
    }
}
