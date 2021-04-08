package guldilin.service;

import guldilin.dto.ProducerDTO;
import guldilin.model.Producer;
import guldilin.repository.ProducerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository, ModelMapper modelMapper) {
        this.producerRepository = producerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProducerDTO> getAll(String title, String address, String contact) {
        List<Producer> workerList;

        if (title != null) {
            workerList = producerRepository.findAllByTitle(title);
        } else if (address != null) {
            workerList = producerRepository.findAllByAddress(address);
        } else if (contact != null) {
            workerList = producerRepository.findAllByContact(contact);
        } else {
            workerList = producerRepository.findAll();
        }
        return workerList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProducerDTO get(Integer id) {
        Optional<Producer> found = producerRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such producer");
    }

    @Override
    public ProducerDTO create(ProducerDTO producerDTO) {
        if (producerRepository.findAllByTitle(producerDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException(" with name already exists");
        }
        return mapToDTO(producerRepository.save(mapToEntity(producerDTO)));
    }

    private ProducerDTO mapToDTO(Producer producer) {
        return modelMapper.map(producer, ProducerDTO.class);
    }

    private Producer mapToEntity(ProducerDTO producerDTO) {
        return modelMapper.map(producerDTO, Producer.class);
    }
}
