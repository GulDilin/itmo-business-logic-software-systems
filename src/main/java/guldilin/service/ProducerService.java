package guldilin.service;

import guldilin.dto.ProducerDTO;

import java.util.List;

public interface ProducerService {
    List<ProducerDTO> getAll(String title, String address, String contact);

    ProducerDTO get(Integer id);

    ProducerDTO create(ProducerDTO processRoleDTO);
}
