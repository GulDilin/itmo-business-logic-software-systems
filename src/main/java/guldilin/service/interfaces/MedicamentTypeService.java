package guldilin.service.interfaces;

import guldilin.dto.MedicamentTypeDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;

import java.util.List;

public interface MedicamentTypeService {
    List<MedicamentTypeDTO> getAll(String title, String description);
    MedicamentTypeDTO get(Integer id) throws NoSuchObject;
    MedicamentTypeDTO create(MedicamentTypeDTO medicamentTypeDTO) throws ObjectAlreadyExists, NoSuchObject;
}
