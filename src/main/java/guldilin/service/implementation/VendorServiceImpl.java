package guldilin.service.implementation;

import guldilin.dto.VendorDTO;
import guldilin.exceptions.NoSuchObject;
import guldilin.exceptions.ObjectAlreadyExists;
import guldilin.model.Vendor;
import guldilin.repository.VendorRepository;
import guldilin.service.interfaces.VendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, ModelMapper modelMapper) {
        this.vendorRepository = vendorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<VendorDTO> getAll(String title, String address, String contact) {
        return vendorRepository.findAll()
                .stream()
                .filter(e -> title == null || e.getTitle().equals(title))
                .filter(e -> address == null || e.getAddress().equals(address))
                .filter(e -> contact == null || e.getContact().equals(contact))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public VendorDTO get(Integer id) throws NoSuchObject {
        return mapToDTO(vendorRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NoSuchObject(Vendor.class.getName())));
    }

    @Override
    public VendorDTO create(VendorDTO vendorDTO) throws ObjectAlreadyExists {
        if (vendorRepository.findAllByTitle(vendorDTO.getTitle()).size() > 0) {
            throw new ObjectAlreadyExists(Vendor.class.getName());
        }
        return mapToDTO(vendorRepository.save(mapToEntity(vendorDTO)));
    }

    private VendorDTO mapToDTO(Vendor vendor) {
        return modelMapper.map(vendor, VendorDTO.class);
    }

    private Vendor mapToEntity(VendorDTO vendorDTO) {
        return modelMapper.map(vendorDTO, Vendor.class);
    }
}
