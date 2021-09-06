package guldilin.service.interfaces;

import guldilin.dto.ActiveSubstanceDTO;

import java.util.List;

public interface ActiveSubstanceService {
    List<ActiveSubstanceDTO> getAll(String title, String description);
    ActiveSubstanceDTO get(Integer id);
    ActiveSubstanceDTO create(ActiveSubstanceDTO activeSubstanceDTO);
}
