package guldilin.service;

import guldilin.dto.ActiveSubstanceDTO;

import java.util.List;

public interface ActiveSubstanceService {
    public List<ActiveSubstanceDTO> getAll(String title, String description);

    public ActiveSubstanceDTO get(Integer id);

    public ActiveSubstanceDTO create(ActiveSubstanceDTO activeSubstanceDTO);
}
