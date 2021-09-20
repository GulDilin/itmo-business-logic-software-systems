package guldilin.service.interfaces;

import guldilin.dto.VendorDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAll(String title, String address, String contact);
    VendorDTO get(Integer id) throws NoSuchObject;
    VendorDTO create(VendorDTO vendorDTO) throws ObjectAlreadyExists;
}
