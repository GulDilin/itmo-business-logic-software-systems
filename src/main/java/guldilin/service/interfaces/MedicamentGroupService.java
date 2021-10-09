package guldilin.service.interfaces;

import guldilin.dto.MedicamentGroupDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;

import java.util.List;

public interface MedicamentGroupService {
    List<MedicamentGroupDTO> getAll(String title, String description);
    MedicamentGroupDTO get(Integer id) throws NoSuchObject;
    MedicamentGroupDTO create(MedicamentGroupDTO medicamentGroupDTO) throws ObjectAlreadyExists;
}
