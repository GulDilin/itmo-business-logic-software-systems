package guldilin.service.implementation;

import guldilin.dto.VendorDTO;
import guldilin.model.Vendor;
import guldilin.repository.VendorRepository;
import guldilin.service.interfaces.VendorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        List<Vendor> workerList;

        if (title != null) {
            workerList = vendorRepository.findAllByTitle(title);
        } else if (address != null) {
            workerList = vendorRepository.findAllByAddress(address);
        } else if (contact != null) {
            workerList = vendorRepository.findAllByContact(contact);
        } else {
            workerList = vendorRepository.findAll();
        }
        return workerList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public VendorDTO get(Integer id) {
        Optional<Vendor> found = vendorRepository.findById(Long.valueOf(id));
        if (found.isPresent()) {
            return mapToDTO(found.get());
        }
        throw new IllegalArgumentException("No such vendor");
    }

    @Override
    public VendorDTO create(VendorDTO vendorDTO) {
        if (vendorRepository.findAllByTitle(vendorDTO.getTitle()).size() > 0) {
            throw new IllegalArgumentException(" with name already exists");
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
