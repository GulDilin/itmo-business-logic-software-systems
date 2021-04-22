package guldilin.service;

import guldilin.dto.VendorDTO;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAll(String title, String address, String contact);
    VendorDTO get(Integer id);
    VendorDTO create(VendorDTO vendorDTO);
}
