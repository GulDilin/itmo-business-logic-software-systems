package guldilin.service;

import guldilin.dto.ProducerDTO;

import java.util.List;

public interface ProducerService {
    public List<ProducerDTO> getAll(String title, String address, String contact);

    public ProducerDTO get(Integer id);

    public ProducerDTO create(ProducerDTO processRoleDTO);
}
